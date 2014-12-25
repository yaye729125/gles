package com.orange.engine;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;

import com.orange.BuildConfig;
import com.orange.audio.music.MusicFactory;
import com.orange.audio.music.MusicManager;
import com.orange.audio.sound.SoundFactory;
import com.orange.audio.sound.SoundManager;
import com.orange.engine.camera.Camera;
import com.orange.engine.handler.DrawHandlerList;
import com.orange.engine.handler.IDrawHandler;
import com.orange.engine.handler.IUpdateHandler;
import com.orange.engine.handler.UpdateHandlerList;
import com.orange.engine.handler.runnable.RunnableHandler;
import com.orange.engine.options.EngineOptions;
import com.orange.entity.scene.group.SceneGroup;
import com.orange.input.sensor.SensorDelay;
import com.orange.input.sensor.acceleration.AccelerationData;
import com.orange.input.sensor.acceleration.AccelerationSensorOptions;
import com.orange.input.sensor.acceleration.IAccelerationListener;
import com.orange.input.sensor.location.ILocationListener;
import com.orange.input.sensor.location.LocationProviderStatus;
import com.orange.input.sensor.location.LocationSensorOptions;
import com.orange.input.sensor.orientation.IOrientationListener;
import com.orange.input.sensor.orientation.OrientationData;
import com.orange.input.sensor.orientation.OrientationSensorOptions;
import com.orange.input.touch.TouchEvent;
import com.orange.input.touch.controller.ITouchController;
import com.orange.input.touch.controller.ITouchEventCallback;
import com.orange.input.touch.controller.MultiTouchController;
import com.orange.input.touch.controller.SingleTouchController;
import com.orange.opengl.font.FontFactory;
import com.orange.opengl.font.FontManager;
import com.orange.opengl.shader.ShaderProgramManager;
import com.orange.opengl.texture.TextureManager;
import com.orange.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import com.orange.opengl.util.GLState;
import com.orange.opengl.vbo.VertexBufferObjectManager;
import com.orange.util.debug.Debug;
import com.orange.util.time.TimeConstants;

/**
 * 引擎类：负责更新动画与修改器的图像，计算实际的绘图坐标，处理用户输入事件（触摸、按键、感应器），并控制整个游戏的执行
 * (c) OrangeGame 2012
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
@SuppressLint("WrongCall")
public class Engine implements SensorEventListener, OnTouchListener, ITouchEventCallback, LocationListener {
	public static final String TAG = Engine.class.getSimpleName();
	// ===========================================================
	// Constants
	// ===========================================================

	private static final SensorDelay SENSORDELAY_DEFAULT = SensorDelay.GAME;
	private static final int UPDATEHANDLERS_CAPACITY_DEFAULT = 8;
	private static final int DRAWHANDLERS_CAPACITY_DEFAULT = 4;

	// ===========================================================
	// Fields
	// ===========================================================

	private boolean mRunning;
	private boolean mDestroyed;

	private long mLastTick;
	private float mSecondsElapsedTotal;

	private final EngineLock mEngineLock;

	private final UpdateThread mUpdateThread;
	private final RunnableHandler mUpdateThreadRunnableHandler = new RunnableHandler();

	private final EngineOptions mEngineOptions;
	protected final Camera mCamera;

	private ITouchController mTouchController;

	private final VertexBufferObjectManager mVertexBufferObjectManager = new VertexBufferObjectManager();
	private final TextureManager mTextureManager = new TextureManager();
	private final FontManager mFontManager = new FontManager();
	private final ShaderProgramManager mShaderProgramManager = new ShaderProgramManager();

	private final SoundManager mSoundManager;
	private final MusicManager mMusicManager;

	protected SceneGroup mSceneGroup;

	private Vibrator mVibrator;

	private ILocationListener mLocationListener;
	private Location mLocation;

	private IAccelerationListener mAccelerationListener;
	private AccelerationData mAccelerationData;

	private IOrientationListener mOrientationListener;
	private OrientationData mOrientationData;

	private final UpdateHandlerList mUpdateHandlers = new UpdateHandlerList(Engine.UPDATEHANDLERS_CAPACITY_DEFAULT);
	private final DrawHandlerList mDrawHandlers = new DrawHandlerList(Engine.DRAWHANDLERS_CAPACITY_DEFAULT);

	protected int mSurfaceWidth = 1; // 1 to prevent accidental DIV/0
	protected int mSurfaceHeight = 1; // 1 to prevent accidental DIV/0

	// ===========================================================
	// Constructors
	// ===========================================================
	/**
	 * 构建引擎
	 * @param pEngineOptions
	 */
	public Engine(final EngineOptions pEngineOptions) {
		/* Initialize Factory and Manager classes. */
		BitmapTextureAtlasTextureRegionFactory.reset();
		SoundFactory.onCreate();
		MusicFactory.onCreate();
		FontFactory.onCreate();
		this.mVertexBufferObjectManager.onCreate();
		this.mTextureManager.onCreate();
		this.mFontManager.onCreate();
		this.mShaderProgramManager.onCreate();

		/* Apply EngineOptions. */
		this.mEngineOptions = pEngineOptions;
		if(this.mEngineOptions.hasEngineLock()) {
			this.mEngineLock = pEngineOptions.getEngineLock();
		} else {
			this.mEngineLock = new EngineLock(false);
		}
		this.mCamera = pEngineOptions.getCamera();

		/* Touch. */
		if(this.mEngineOptions.getTouchOptions().needsMultiTouch()) {
			this.setTouchController(new MultiTouchController());
		} else {
			this.setTouchController(new SingleTouchController());
		}

		/* Audio. */
		if(this.mEngineOptions.getAudioOptions().needsSound()) {
			this.mSoundManager = new SoundManager(this.mEngineOptions.getAudioOptions().getSoundOptions().getMaxSimultaneousStreams());
		} else {
			this.mSoundManager = null;
		}
		if(this.mEngineOptions.getAudioOptions().needsMusic()) {
			this.mMusicManager = new MusicManager();
		} else {
			this.mMusicManager = null;
		}

		/* Start the UpdateThread. */
		if(this.mEngineOptions.hasUpdateThread()) {
			this.mUpdateThread = this.mEngineOptions.getUpdateThread();
		} else {
			this.mUpdateThread = new UpdateThread();
		}
		this.mUpdateThread.setEngine(this);
	}

	/**
	 * 开启UI更新处理
	 * @throws IllegalThreadStateException
	 */
	public void startUpdateThread() throws IllegalThreadStateException {
		this.mUpdateThread.start();
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * 引擎是否运行中
	 * @return
	 */
	public synchronized boolean isRunning() {
		return this.mRunning;
	}

	/**
	 * 引擎开始运行
	 */
	public synchronized void start() {
		if(!this.mRunning) {
			this.mLastTick = System.nanoTime();
			this.mRunning = true;
		}
	}

	/**
	 * 引擎停止运行
	 */
	public synchronized void stop() {
		if(this.mRunning) {
			this.mRunning = false;
		}
	}

	/**
	 * 该Enginelock可以用来锁定，以确保代码运行之间update线程和GL线程互斥。
	 *当调用者已经在updatethread或GL线程，该代码是立即执行。
	 *“返回enginelock发动机锁，确保update线程和gl线程互斥。
	 */
	public EngineLock getEngineLock() {
		return this.mEngineLock;
	}


	public EngineOptions getEngineOptions() {
		return this.mEngineOptions;
	}

	public Camera getCamera() {
		return this.mCamera;
	}

	public float getSecondsElapsedTotal() {
		return this.mSecondsElapsedTotal;
	}

	public void setSurfaceSize(final int pSurfaceWidth, final int pSurfaceHeight) {
		this.mSurfaceWidth = pSurfaceWidth;
		this.mSurfaceHeight = pSurfaceHeight;
		this.onUpdateCameraSurface();
	}

	protected void onUpdateCameraSurface() {
		this.mCamera.setSurfaceSize(0, 0, this.mSurfaceWidth, this.mSurfaceHeight);
	}
	
	public void setSceneGroup(SceneGroup mSceneGroup) {
		this.mSceneGroup = mSceneGroup;
	}

	public int getSurfaceWidth() {
		return this.mSurfaceWidth;
	}

	public int getSurfaceHeight() {
		return this.mSurfaceHeight;
	}

	public ITouchController getTouchController() {
		return this.mTouchController;
	}

	public void setTouchController(final ITouchController pTouchController) {
		this.mTouchController = pTouchController;
		this.mTouchController.setTouchEventCallback(this);
	}
	
	public AccelerationData getAccelerationData() {
		return this.mAccelerationData;
	}

	public OrientationData getOrientationData() {
		return this.mOrientationData;
	}

	public VertexBufferObjectManager getVertexBufferObjectManager() {
		return this.mVertexBufferObjectManager;
	}

	public TextureManager getTextureManager() {
		return this.mTextureManager;
	}

	public FontManager getFontManager() {
		return this.mFontManager;
	}

	public ShaderProgramManager getShaderProgramManager() {
		return this.mShaderProgramManager;
	}

	public SoundManager getSoundManager() throws IllegalStateException {
		if(this.mSoundManager != null) {
			return this.mSoundManager;
		} else {
			throw new IllegalStateException("To enable the SoundManager, check the EngineOptions!");
		}
	}

	public MusicManager getMusicManager() throws IllegalStateException {
		if(this.mMusicManager != null) {
			return this.mMusicManager;
		} else {
			throw new IllegalStateException("To enable the MusicManager, check the EngineOptions!");
		}
	}

	public void registerUpdateHandler(final IUpdateHandler pUpdateHandler) {
		this.mUpdateHandlers.add(pUpdateHandler);
	}

	public void unregisterUpdateHandler(final IUpdateHandler pUpdateHandler) {
		this.mUpdateHandlers.remove(pUpdateHandler);
	}

	public void clearUpdateHandlers() {
		this.mUpdateHandlers.clear();
	}
	
	public boolean containsDrawHandler(final IDrawHandler pDrawHandler){
		return this.mDrawHandlers.contains(pDrawHandler);
	}

	public void registerDrawHandler(final IDrawHandler pDrawHandler) {
		this.mDrawHandlers.add(pDrawHandler);
	}

	public void unregisterDrawHandler(final IDrawHandler pDrawHandler) {
		this.mDrawHandlers.remove(pDrawHandler);
	}

	public void clearDrawHandlers() {
		this.mDrawHandlers.clear();
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onAccuracyChanged(final Sensor pSensor, final int pAccuracy) {
		if(this.mRunning) {
			switch(pSensor.getType()) {
				case Sensor.TYPE_ACCELEROMETER:
					if(this.mAccelerationData != null) {
						this.mAccelerationData.setAccuracy(pAccuracy);
						this.mAccelerationListener.onAccelerationAccuracyChanged(this.mAccelerationData);
					} else if(this.mOrientationData != null) {
						this.mOrientationData.setAccelerationAccuracy(pAccuracy);
						this.mOrientationListener.onOrientationAccuracyChanged(this.mOrientationData);
					}
					break;
				case Sensor.TYPE_MAGNETIC_FIELD:
					this.mOrientationData.setMagneticFieldAccuracy(pAccuracy);
					this.mOrientationListener.onOrientationAccuracyChanged(this.mOrientationData);
					break;
			}
		}
	}

	@Override
	public void onSensorChanged(final SensorEvent pEvent) {
		if(this.mRunning) {
			switch(pEvent.sensor.getType()) {
				case Sensor.TYPE_ACCELEROMETER:
					if(this.mAccelerationData != null) {
						this.mAccelerationData.setValues(pEvent.values);
						this.mAccelerationListener.onAccelerationChanged(this.mAccelerationData);
					} else if(this.mOrientationData != null) {
						this.mOrientationData.setAccelerationValues(pEvent.values);
						this.mOrientationListener.onOrientationChanged(this.mOrientationData);
					}
					break;
				case Sensor.TYPE_MAGNETIC_FIELD:
					this.mOrientationData.setMagneticFieldValues(pEvent.values);
					this.mOrientationListener.onOrientationChanged(this.mOrientationData);
					break;
			}
		}
	}

	@Override
	public void onLocationChanged(final Location pLocation) {
		if(this.mLocation == null) {
			this.mLocation = pLocation;
		} else {
			if(pLocation == null) {
				this.mLocationListener.onLocationLost();
			} else {
				this.mLocation = pLocation;
				this.mLocationListener.onLocationChanged(pLocation);
			}
		}
	}

	@Override
	public void onProviderDisabled(final String pProvider) {
		this.mLocationListener.onLocationProviderDisabled();
	}

	@Override
	public void onProviderEnabled(final String pProvider) {
		this.mLocationListener.onLocationProviderEnabled();
	}

	@Override
	public void onStatusChanged(final String pProvider, final int pStatus, final Bundle pExtras) {
		switch(pStatus) {
			case LocationProvider.AVAILABLE:
				this.mLocationListener.onLocationProviderStatusChanged(LocationProviderStatus.AVAILABLE, pExtras);
				break;
			case LocationProvider.OUT_OF_SERVICE:
				this.mLocationListener.onLocationProviderStatusChanged(LocationProviderStatus.OUT_OF_SERVICE, pExtras);
				break;
			case LocationProvider.TEMPORARILY_UNAVAILABLE:
				this.mLocationListener.onLocationProviderStatusChanged(LocationProviderStatus.TEMPORARILY_UNAVAILABLE, pExtras);
				break;
		}
	}

	@Override
	public boolean onTouch(final View pView, final MotionEvent pSurfaceMotionEvent) {
		if(this.mRunning) {
			this.mTouchController.onHandleMotionEvent(pSurfaceMotionEvent);
			try {
				/* Because a human cannot interact 1000x per second, we pause the UI-Thread for a little. */
				Thread.sleep(this.mEngineOptions.getTouchOptions().getTouchEventIntervalMilliseconds());
			} catch (final InterruptedException e) {
				Debug.e(e);
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean onTouchEvent(final TouchEvent pSurfaceTouchEvent) {
		/*
		 * Let the engine determine which scene and camera this event should be
		 * handled by.
		 */
		final SceneGroup sceneGroup = this.getSceneFromSurfaceTouchEvent(pSurfaceTouchEvent);
		final Camera camera = this.getCameraFromSurfaceTouchEvent(pSurfaceTouchEvent);

		this.convertSurfaceToSceneTouchEvent(camera, pSurfaceTouchEvent);

		return this.onTouchScene(sceneGroup, pSurfaceTouchEvent);
	}

	protected boolean onTouchScene(final SceneGroup pSceneGroup, final TouchEvent pSceneTouchEvent) {
		if(pSceneGroup != null) {
			return pSceneGroup.onSceneTouchEvent(pSceneTouchEvent);
		} else {
			return false;
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void runOnUpdateThread(final Runnable pRunnable) {
		this.runOnUpdateThread(pRunnable, true);
	}

	/**
	 * This method is useful when you want to execute code on the {@link UpdateThread}, even though the Engine is paused.
	 *
	 * @param pRunnable the {@link Runnable} to be run on the {@link UpdateThread}.
	 * @param pOnlyWhenEngineRunning if <code>true</code>, the execution of the {@link Runnable} will be delayed until the next time {@link Engine#onUpdateUpdateHandlers(float)} is picked up, which is when {@link Engine#isRunning()} is <code>true</code>.
	 * 								 if <code>false</code>, the execution of the {@link Runnable} will happen as soon as possible on the {@link UpdateThread}, no matter what {@link Engine#isRunning()} is.
	 */
	public void runOnUpdateThread(final Runnable pRunnable, final boolean pOnlyWhenEngineRunning) {
		if(pOnlyWhenEngineRunning) {
			this.mUpdateThreadRunnableHandler.postRunnable(pRunnable);
		} else {
			this.mUpdateThread.postRunnable(pRunnable);
		}
	}
	
	/**
	 * 通过enginelock确保线程安全运行
	 * @param pRunnable
	 */
	public void runSafely(final Runnable pRunnable) {
		this.mEngineLock.lock();
		try {
			pRunnable.run();
		} finally {
			this.mEngineLock.unlock();
		}
	}

	/**
	 * 注销engine
	 */
	public void onDestroy() {
		this.mEngineLock.lock();
		try {
			this.mDestroyed = true;
			this.mEngineLock.notifyCanUpdate();
		} finally {
			this.mEngineLock.unlock();
		}
		try {
			this.mUpdateThread.join();
		} catch (final InterruptedException e) {
			Debug.e("Could not join UpdateThread.", e);
			Debug.w("Trying to manually interrupt UpdateThread.");
			this.mUpdateThread.interrupt();
		}

		this.mVertexBufferObjectManager.onDestroy();
		this.mTextureManager.onDestroy();
		this.mFontManager.onDestroy();
		this.mShaderProgramManager.onDestroy();
	}
	
	/**
	 * 重新加载资源
	 */
	public void onReloadResources() {
		this.mVertexBufferObjectManager.onReload();
		this.mTextureManager.onReload();
		this.mFontManager.onReload();
		this.mShaderProgramManager.onReload();
	}

	protected Camera getCameraFromSurfaceTouchEvent(final TouchEvent pTouchEvent) {
		return this.getCamera();
	}

	protected SceneGroup getSceneFromSurfaceTouchEvent(final TouchEvent pTouchEvent) {
		return this.mSceneGroup;
	}

	protected void convertSurfaceToSceneTouchEvent(final Camera pCamera, final TouchEvent pSurfaceTouchEvent) {
		pCamera.convertSurfaceToSceneTouchEvent(pSurfaceTouchEvent, this.mSurfaceWidth, this.mSurfaceHeight);
	}

	protected void convertSceneToSurfaceTouchEvent(final Camera pCamera, final TouchEvent pSurfaceTouchEvent) {
		pCamera.convertSceneToSurfaceTouchEvent(pSurfaceTouchEvent, this.mSurfaceWidth, this.mSurfaceHeight);
	}

	void onTickUpdate() throws InterruptedException {
		if(this.mRunning) {
			final long secondsElapsed = this.getNanosecondsElapsed();

			this.mEngineLock.lock();
			try {
				this.throwOnDestroyed();

				this.onUpdate(secondsElapsed);

				this.throwOnDestroyed();
				
				if (this.mEngineLock.isNeedDraw()) {
					this.mEngineLock.notifyCanDraw();
					this.mEngineLock.waitUntilCanUpdate();
				}else {
					this.mEngineLock.signalAll();
				}
			} finally {
				this.mEngineLock.unlock();
			}
		} else {
			this.mEngineLock.lock();
			try {
				this.throwOnDestroyed();

				if (this.mEngineLock.isNeedDraw()) {
//					this.mEngineLock.notifyCanDraw();
//					this.mEngineLock.waitUntilCanUpdate();
				}else {
					this.mEngineLock.signalAll();
				}
				Thread.sleep(30);
			} finally {
				this.mEngineLock.unlock();
			}
			
		}
	}

	private void throwOnDestroyed() throws EngineDestroyedException {
		if(this.mDestroyed) {
			throw new EngineDestroyedException();
		}
	}

	public void onUpdate(final long pNanosecondsElapsed) throws InterruptedException {
		final float pSecondsElapsed = pNanosecondsElapsed * TimeConstants.SECONDS_PER_NANOSECOND;

		this.mSecondsElapsedTotal += pSecondsElapsed;
		this.mLastTick += pNanosecondsElapsed;

		this.mTouchController.onUpdate(pSecondsElapsed);
		this.onUpdateUpdateHandlers(pSecondsElapsed);
		this.onUpdateScene(pSecondsElapsed);
	}

	protected void onUpdateScene(final float pSecondsElapsed) {
		if(this.mSceneGroup != null) {
			this.mSceneGroup.onUpdate(pSecondsElapsed);
		}
	}

	protected void onUpdateUpdateHandlers(final float pSecondsElapsed) {
		this.mUpdateThreadRunnableHandler.onUpdate(pSecondsElapsed);
		this.mUpdateHandlers.onUpdate(pSecondsElapsed);
		this.getCamera().onUpdate(pSecondsElapsed);
	}

	protected void onUpdateDrawHandlers(final GLState pGLState, final Camera pCamera) {
		this.mDrawHandlers.onDraw(pGLState, pCamera);
	}
	
	/**
	 * 绘制窗体
	 * @param pGLState
	 * @throws InterruptedException
	 */
	public void onDrawFrame(final GLState pGLState) throws InterruptedException {
		final EngineLock engineLock = this.mEngineLock;

		engineLock.setNeedDraw(true);
		engineLock.lock();
		try {
			engineLock.waitUntilCanDraw();
			engineLock.setNeedDraw(false);
			this.mVertexBufferObjectManager.updateVertexBufferObjects(pGLState);
			this.mTextureManager.updateTextures(pGLState);
			this.mFontManager.updateFonts(pGLState);

			this.onUpdateDrawHandlers(pGLState, this.mCamera);
			this.onDrawScene(pGLState, this.mCamera);

			engineLock.notifyCanUpdate();
		} finally {
			engineLock.unlock();
		}
	}

	/**
	 * 绘制场景
	 * @param pGLState
	 * @param pCamera
	 */
	protected void onDrawScene(final GLState pGLState, final Camera pCamera) {
		if(this.mSceneGroup != null) {
			this.mSceneGroup.onDraw(pGLState, pCamera);
		}

	}
	
	/**
	 * 获取间隔时间
	 * @return
	 */
	private long getNanosecondsElapsed() {
		final long now = System.nanoTime();

		return now - this.mLastTick;
	}
	
	/**
	 * 抖动是否有效
	 * @param pContext
	 * @return
	 */
	public boolean enableVibrator(final Context pContext) {
		this.mVibrator = (Vibrator) pContext.getSystemService(Context.VIBRATOR_SERVICE);
		return this.mVibrator != null;
	}

	/**
	 * 抖动：通过固定频率震动
	 * @param pMilliseconds 抖动频率
	 * @throws IllegalStateException 抖动不可用则抛出异常
	 */
	public void vibrate(final long pMilliseconds) throws IllegalStateException {
		if(this.mVibrator != null) {
			this.mVibrator.vibrate(pMilliseconds);
		} else {
			throw new IllegalStateException("You need to enable the Vibrator before you can use it!");
		}
	}

	/**
	 * 抖动
	 * @param pPattern 控制抖动开关的数组
	 * @param pRepeat 正数且最大可以设置为3，超过3会报java.lang.ArrayIndexOutOfBoundsException异常，当为正数0、1、2、3时，震得频率根据数字可以分为几种：
				当为0、2时：震动循环震下去（加快的震动）;当为1时：循环震动下去（缓慢的震动）;当为3时：震动只会震一下。如果repeat为负数时只震动一下（对负数没有限制）
	 * @throws IllegalStateException
	 */
	public void vibrate(final long[] pPattern, final int pRepeat) throws IllegalStateException {
		if(this.mVibrator != null) {
			this.mVibrator.vibrate(pPattern, pRepeat);
		} else {
			throw new IllegalStateException("You need to enable the Vibrator before you can use it!");
		}
	}

	public void enableLocationSensor(final Context pContext, final ILocationListener pLocationListener, final LocationSensorOptions pLocationSensorOptions) {
		this.mLocationListener = pLocationListener;

		final LocationManager locationManager = (LocationManager) pContext.getSystemService(Context.LOCATION_SERVICE);
		final String locationProvider = locationManager.getBestProvider(pLocationSensorOptions, pLocationSensorOptions.isEnabledOnly());
		// TODO locationProvider can be null, in that case return false. Successful case should return true.
		locationManager.requestLocationUpdates(locationProvider, pLocationSensorOptions.getMinimumTriggerTime(), pLocationSensorOptions.getMinimumTriggerDistance(), this);

		this.onLocationChanged(locationManager.getLastKnownLocation(locationProvider));
	}

	public void disableLocationSensor(final Context pContext) {
		final LocationManager locationManager = (LocationManager) pContext.getSystemService(Context.LOCATION_SERVICE);
		locationManager.removeUpdates(this);
	}

	/**
	 * @see {@link Engine#enableAccelerationSensor(Context, IAccelerationListener, AccelerationSensorOptions)}
	 */
	public boolean enableAccelerationSensor(final Context pContext, final IAccelerationListener pAccelerationListener) {
		return this.enableAccelerationSensor(pContext, pAccelerationListener, new AccelerationSensorOptions(Engine.SENSORDELAY_DEFAULT));
	}

	/**
	 * @return <code>true</code> when the sensor was successfully enabled, <code>false</code> otherwise.
	 */
	public boolean enableAccelerationSensor(final Context pContext, final IAccelerationListener pAccelerationListener, final AccelerationSensorOptions pAccelerationSensorOptions) {
		final SensorManager sensorManager = (SensorManager) pContext.getSystemService(Context.SENSOR_SERVICE);
		if(Engine.isSensorSupported(sensorManager, Sensor.TYPE_ACCELEROMETER)) {
			this.mAccelerationListener = pAccelerationListener;

			if(this.mAccelerationData == null) {
				final Display display = ((WindowManager) pContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
				final int displayRotation = display.getOrientation();
				this.mAccelerationData = new AccelerationData(displayRotation);
			}

			this.registerSelfAsSensorListener(sensorManager, Sensor.TYPE_ACCELEROMETER, pAccelerationSensorOptions.getSensorDelay());

			return true;
		} else {
			return false;
		}
	}


	/**
	 * @return <code>true</code> when the sensor was successfully disabled, <code>false</code> otherwise.
	 */
	public boolean disableAccelerationSensor(final Context pContext) {
		final SensorManager sensorManager = (SensorManager) pContext.getSystemService(Context.SENSOR_SERVICE);
		if(Engine.isSensorSupported(sensorManager, Sensor.TYPE_ACCELEROMETER)) {
			this.unregisterSelfAsSensorListener(sensorManager, Sensor.TYPE_ACCELEROMETER);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @see {@link Engine#enableOrientationSensor(Context, IOrientationListener, OrientationSensorOptions)}
	 */
	public boolean enableOrientationSensor(final Context pContext, final IOrientationListener pOrientationListener) {
		return this.enableOrientationSensor(pContext, pOrientationListener, new OrientationSensorOptions(Engine.SENSORDELAY_DEFAULT));
	}

	/**
	 * @return <code>true</code> when the sensor was successfully enabled, <code>false</code> otherwise.
	 */
	public boolean enableOrientationSensor(final Context pContext, final IOrientationListener pOrientationListener, final OrientationSensorOptions pOrientationSensorOptions) {
		final SensorManager sensorManager = (SensorManager) pContext.getSystemService(Context.SENSOR_SERVICE);
		if(Engine.isSensorSupported(sensorManager, Sensor.TYPE_ACCELEROMETER) && Engine.isSensorSupported(sensorManager, Sensor.TYPE_MAGNETIC_FIELD)) {
			this.mOrientationListener = pOrientationListener;

			if(this.mOrientationData == null) {
				final Display display = ((WindowManager) pContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
				final int displayRotation = display.getOrientation();
				this.mOrientationData = new OrientationData(displayRotation);
			}

			this.registerSelfAsSensorListener(sensorManager, Sensor.TYPE_ACCELEROMETER, pOrientationSensorOptions.getSensorDelay());
			this.registerSelfAsSensorListener(sensorManager, Sensor.TYPE_MAGNETIC_FIELD, pOrientationSensorOptions.getSensorDelay());

			return true;
		} else {
			return false;
		}
	}


	/**
	 * 方位传感器是否有效
	 * @return <code>true</code> 可以 <code>false</code> 不能.
	 */
	public boolean disableOrientationSensor(final Context pContext) {
		final SensorManager sensorManager = (SensorManager) pContext.getSystemService(Context.SENSOR_SERVICE);
		if(Engine.isSensorSupported(sensorManager, Sensor.TYPE_ACCELEROMETER) && Engine.isSensorSupported(sensorManager, Sensor.TYPE_MAGNETIC_FIELD)) {
			this.unregisterSelfAsSensorListener(sensorManager, Sensor.TYPE_ACCELEROMETER);
			this.unregisterSelfAsSensorListener(sensorManager, Sensor.TYPE_MAGNETIC_FIELD);
			return true;
		} else {
			return false;
		}
	}

	private static boolean isSensorSupported(final SensorManager pSensorManager, final int pType) {
		return pSensorManager.getSensorList(pType).size() > 0;
	}

	private void registerSelfAsSensorListener(final SensorManager pSensorManager, final int pType, final SensorDelay pSensorDelay) {
		final Sensor sensor = pSensorManager.getSensorList(pType).get(0);
		pSensorManager.registerListener(this, sensor, pSensorDelay.getDelay());
	}

	private void unregisterSelfAsSensorListener(final SensorManager pSensorManager, final int pType) {
		final Sensor sensor = pSensorManager.getSensorList(pType).get(0);
		pSensorManager.unregisterListener(this, sensor);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	/**
	 * 更新线程：可用于处理渲染更新
	 * @author OrangeGame <OGEngine@orangegame.cn>
	 *
	 */
	public static class UpdateThread extends Thread {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Fields
		// ===========================================================

		private Engine mEngine;
		private final RunnableHandler mRunnableHandler = new RunnableHandler();

		// ===========================================================
		// Constructors
		// ===========================================================

		public UpdateThread() {
			super(UpdateThread.class.getSimpleName());
		}

		// ===========================================================
		// Getter & Setter
		// ===========================================================

		public void setEngine(final Engine pEngine) {
			this.mEngine = pEngine;
		}

		// ===========================================================
		// Methods for/from SuperClass/Interfaces
		// ===========================================================

		@Override
		public void run() {
			android.os.Process.setThreadPriority(this.mEngine.getEngineOptions().getUpdateThreadPriority());
			try {
				while(true) {
					this.mRunnableHandler.onUpdate(0);
					this.mEngine.onTickUpdate();
				}
			} catch (final InterruptedException e) {
				if(BuildConfig.DEBUG) {
					Debug.d(this.getClass().getSimpleName() + " interrupted. Don't worry - this " + e.getClass().getSimpleName() + " is most likely expected!", e);
				}
				this.interrupt();
			}
		}

		// ===========================================================
		// Methods
		// ===========================================================

		public void postRunnable(final Runnable pRunnable) {
			this.mRunnableHandler.postRunnable(pRunnable);
		}

		// ===========================================================
		// Inner and Anonymous Classes
		// ===========================================================
	}

	/**
	 * 引擎销毁异常
	 * @author OrangeGame <OGEngine@orangegame.cn>
	 *
	 */
	public class EngineDestroyedException extends InterruptedException {
		// ===========================================================
		// Constants
		// ===========================================================

		private static final long serialVersionUID = -4691263961728972560L;

		// ===========================================================
		// Fields
		// ===========================================================

		// ===========================================================
		// Constructors
		// ===========================================================

		// ===========================================================
		// Getter & Setter
		// ===========================================================

		// ===========================================================
		// Methods for/from SuperClass/Interfaces
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================

		// ===========================================================
		// Inner and Anonymous Classes
		// ===========================================================
	}

	/**
	 * 控制引擎线程锁
	 * @author OrangeGame <OGEngine@orangegame.cn>
	 *
	 */
	public static class EngineLock extends ReentrantLock {
		// ===========================================================
		// Constants
		// ===========================================================

		private static final long serialVersionUID = 671220941302523934L;

		// ===========================================================
		// Fields
		// ===========================================================

		final Condition mDrawingCondition = this.newCondition();
		final AtomicBoolean mDrawing = new AtomicBoolean(false);
		final AtomicBoolean mNeedDraw = new AtomicBoolean();

		// ===========================================================
		// Constructors
		// ===========================================================

		public EngineLock(final boolean pFair) {
			super(pFair);
		}

		// ===========================================================
		// Getter & Setter
		// ===========================================================

		// ===========================================================
		// Methods for/from SuperClass/Interfaces
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================

		void signalAll(){
//			Debug.d("Engine.signalAll" + " @(Thread: '" + Thread.currentThread().getName() + "')");
			this.mDrawingCondition.signalAll();
		}
		
		void notifyCanDraw() {
			this.mDrawing.set(true);
			this.mDrawingCondition.signalAll();
		}

		void notifyCanUpdate() {
			this.mDrawing.set(false);
			this.mDrawingCondition.signalAll();
		}

		void waitUntilCanDraw() throws InterruptedException {
			while(!this.mDrawing.get()) {
				this.mDrawingCondition.await();
			}
		}

		void waitUntilCanUpdate() throws InterruptedException {
			while(this.mDrawing.get()) {
				this.mDrawingCondition.await();
			}
		}
		
		void setNeedDraw(boolean pNeedDraw){
			this.mNeedDraw.set(pNeedDraw);
		}

		boolean isNeedDraw(){
			return this.mNeedDraw.get();
		}
		
		// ===========================================================
		// Inner and Anonymous Classes
		// ===========================================================
	}
}
