package com.orange.ui.activity;

import com.orange.BuildConfig;
import com.orange.audio.music.MusicManager;
import com.orange.audio.sound.SoundManager;
import com.orange.engine.Engine;
import com.orange.engine.options.EngineOptions;
import com.orange.engine.options.ScreenOrientation;
import com.orange.engine.options.WakeLockOptions;
import com.orange.entity.scene.BaseScene;
import com.orange.entity.scene.group.SceneGroup;
import com.orange.event.EventDispatcher;
import com.orange.input.sensor.acceleration.AccelerationSensorOptions;
import com.orange.input.sensor.acceleration.IAccelerationListener;
import com.orange.input.sensor.location.ILocationListener;
import com.orange.input.sensor.location.LocationSensorOptions;
import com.orange.input.sensor.orientation.IOrientationListener;
import com.orange.input.sensor.orientation.OrientationSensorOptions;
import com.orange.opengl.font.FontManager;
import com.orange.opengl.shader.ShaderProgramManager;
import com.orange.opengl.texture.TextureManager;
import com.orange.opengl.util.GLState;
import com.orange.opengl.vbo.VertexBufferObjectManager;
import com.orange.opengl.view.IRendererListener;
import com.orange.opengl.view.RenderSurfaceView;
import com.orange.res.ResManager;
import com.orange.ui.IGameInterface;
import com.orange.util.ActivityUtils;
import com.orange.util.Constants;
import com.orange.util.debug.Debug;
import com.orange.util.system.SystemUtils;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Gravity;
import android.widget.FrameLayout.LayoutParams;

/**
 * (c) OrangeGame 2012 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public abstract class BaseGameActivity extends BaseActivity implements
		IGameInterface, IRendererListener {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	protected Engine mEngine;

	private WakeLock mWakeLock;

	protected RenderSurfaceView mRenderSurfaceView;
	/**
	 * 用于onPaused标记
	 */
	private boolean mIsRenderSurfaceViewPaused = false;

	private boolean mGamePaused;
	private boolean mGameCreated;
	private boolean mCreateGameCalled;
	private boolean mOnReloadResourcesScheduled;

	// ===========================================================
	// Constructors
	// ===========================================================

	@Override
	protected void onCreate(final Bundle pSavedInstanceState) {
		if (BuildConfig.DEBUG) {
			Debug.d(this.getClass().getSimpleName() + ".onCreate"
					+ " @(Thread: '" + Thread.currentThread().getName() + "')");
		}
		super.onCreate(pSavedInstanceState);
		this.mGamePaused = true;
		this.mEngine = this.onCreateEngine(this.onCreateEngineOptions());
		this.mEngine.startUpdateThread();
		ResManager.init(this, this.mEngine);
		EventDispatcher.init();
		this.applyEngineOptions();
		this.onSetContentView();
	}

	@Override
	public Engine onCreateEngine(final EngineOptions pEngineOptions) {
		return new Engine(pEngineOptions);
	}

	@Override
	public synchronized void onSurfaceCreated(final GLState pGLState) {
		if (BuildConfig.DEBUG) {
			Debug.d(this.getClass().getSimpleName() + ".onSurfaceCreated"
					+ " @(Thread: '" + Thread.currentThread().getName() + "')");
		}

		if (this.mGameCreated) {
			this.onReloadResources();

			if (this.mGamePaused && this.mGameCreated) {
				// this.onResumeGame();
			}
		} else {
			if (this.mCreateGameCalled) {
				this.mOnReloadResourcesScheduled = true;
			} else {
				this.mCreateGameCalled = true;
				this.onCreateGame();
			}
		}
	}

	@Override
	public synchronized void onSurfaceChanged(final GLState pGLState,
			final int pWidth, final int pHeight) {
		if (BuildConfig.DEBUG) {
			Debug.d(this.getClass().getSimpleName()
					+ ".onSurfaceChanged(Width=" + pWidth + ",  Height="
					+ pHeight + ")" + " @(Thread: '"
					+ Thread.currentThread().getName() + "')");
		}
	}

	protected synchronized void onCreateGame() {
		if (BuildConfig.DEBUG) {
			// Debug.d(this.getClass().getSimpleName() + ".onCreateGame" +
			// " @(Thread: '" + Thread.currentThread().getName() + "')");
		}

		final OnPopulateSceneCallback onPopulateSceneCallback = new OnPopulateSceneCallback() {
			@Override
			public void onPopulateSceneFinished() {
				try {
					if (BuildConfig.DEBUG) {
						// Debug.d(BaseGameActivity.this.getClass().getSimpleName()
						// + ".onGameCreated" + " @(Thread: '" +
						// Thread.currentThread().getName() + "')");
					}

					BaseGameActivity.this.onGameCreated();
				} catch (final Throwable pThrowable) {
					Debug.e(BaseGameActivity.this.getClass().getSimpleName()
							+ ".onGameCreated failed." + " @(Thread: '"
							+ Thread.currentThread().getName() + "')",
							pThrowable);
				}

				// BaseGameActivity.this.callGameResumedOnUIThread();
				BaseGameActivity.this.onResumeGame();
			}
		};

		final OnCreateSceneCallback onCreateSceneCallback = new OnCreateSceneCallback() {
			@Override
			public void onCreateSceneFinished(final SceneGroup pSceneGroup) {
				BaseGameActivity.this.mEngine.setSceneGroup(pSceneGroup);

				try {
					if (BuildConfig.DEBUG) {
						// Debug.d(BaseGameActivity.this.getClass().getSimpleName()
						// + ".onPopulateScene" + " @(Thread: '" +
						// Thread.currentThread().getName() + "')");
					}

					BaseGameActivity.this.onPopulateScene(pSceneGroup,
							onPopulateSceneCallback);
				} catch (final Throwable pThrowable) {
					Debug.e(BaseGameActivity.this.getClass().getSimpleName()
							+ ".onPopulateScene failed." + " @(Thread: '"
							+ Thread.currentThread().getName() + "')",
							pThrowable);
				}
			}
		};

		final OnCreateResourcesCallback onCreateResourcesCallback = new OnCreateResourcesCallback() {
			@Override
			public void onCreateResourcesFinished() {
				try {
					if (BuildConfig.DEBUG) {
						// Debug.d(BaseGameActivity.this.getClass().getSimpleName()
						// + ".onCreateScene" + " @(Thread: '" +
						// Thread.currentThread().getName() + "')");
					}

					BaseGameActivity.this.onCreateScene(onCreateSceneCallback);
				} catch (final Throwable pThrowable) {
					Debug.e(BaseGameActivity.this.getClass().getSimpleName()
							+ ".onCreateScene failed." + " @(Thread: '"
							+ Thread.currentThread().getName() + "')",
							pThrowable);
				}
			}
		};

		try {
			if (BuildConfig.DEBUG) {
				// Debug.d(this.getClass().getSimpleName() +
				// ".onCreateResources" + " @(Thread: '" +
				// Thread.currentThread().getName() + "')");
			}

			this.onCreateResources(onCreateResourcesCallback);
		} catch (final Throwable pThrowable) {
			Debug.e(this.getClass().getSimpleName() + ".onCreateGame failed."
					+ " @(Thread: '" + Thread.currentThread().getName() + "')",
					pThrowable);
		}
	}

	@Override
	public synchronized void onGameCreated() {
		this.mGameCreated = true;

		/*
		 * Since the potential asynchronous resource creation, the surface might
		 * already be invalid and a resource reloading might be necessary.
		 */
		if (this.mOnReloadResourcesScheduled) {
			this.mOnReloadResourcesScheduled = false;
			try {
				this.onReloadResources();
			} catch (final Throwable pThrowable) {
				Debug.e(this.getClass().getSimpleName()
						+ ".onReloadResources failed." + " @(Thread: '"
						+ Thread.currentThread().getName() + "')", pThrowable);
			}
		}
	}

	@Override
	protected synchronized void onResume() {
		if (BuildConfig.DEBUG) {
			Debug.d(this.getClass().getSimpleName() + ".onResume"
					+ " @(Thread: '" + Thread.currentThread().getName() + "')");
		}

		super.onResume();
		this.acquireWakeLock();
	}

	public synchronized void doResumeGame() {
		this.mRenderSurfaceView.doResume();
		this.onResumeGame();
	}

	@Override
	public synchronized void onResumeGame() {
		if (BuildConfig.DEBUG) {
			Debug.d(this.getClass().getSimpleName() + ".onResumeGame"
					+ " @(Thread: '" + Thread.currentThread().getName() + "')");
		}

		this.mEngine.start();

		this.mGamePaused = false;
	}

	@Override
	public synchronized void onWindowFocusChanged(final boolean pHasWindowFocus) {
		if (BuildConfig.DEBUG) {
			Debug.d(this.getClass().getSimpleName() + ".onWindowFocusChanged"
					+ " @(Thread: '" + Thread.currentThread().getName() + "')");
		}
		super.onWindowFocusChanged(pHasWindowFocus);

		if (pHasWindowFocus && this.mGamePaused && this.mGameCreated) {
			// this.onResumeGame();
		}

		if (pHasWindowFocus) {
			if (this.mIsRenderSurfaceViewPaused) {
				this.doPauseGame();
				this.mIsRenderSurfaceViewPaused = false;
			}
		} else {
			this.mIsRenderSurfaceViewPaused = this.mRenderSurfaceView.isPause();
			this.doResumeGame();
		}
	}

	@Override
	public void onReloadResources() {
		if (BuildConfig.DEBUG) {
			Debug.d(this.getClass().getSimpleName() + ".onReloadResources"
					+ " @(Thread: '" + Thread.currentThread().getName() + "')");
		}

		this.mEngine.onReloadResources();
	}

	@Override
	protected synchronized void onPause() {
		if (BuildConfig.DEBUG) {
			Debug.d(this.getClass().getSimpleName() + ".onPause"
					+ " @(Thread: '" + Thread.currentThread().getName() + "')");
		}

		super.onPause();

		// this.mRenderSurfaceView.onPause();
		this.releaseWakeLock();

		if (!this.mGamePaused) {
			// this.onPauseGame();
		}
	}

	/**
	 * 暂停游戏
	 */
	public synchronized void doPauseGame() {
		this.mRenderSurfaceView.doPause();
		if (!this.mGamePaused) {
			this.onPauseGame();
		}
	}

	@Override
	public synchronized void onPauseGame() {
		if (BuildConfig.DEBUG) {
			Debug.d(this.getClass().getSimpleName() + ".onPauseGame"
					+ " @(Thread: '" + Thread.currentThread().getName() + "')");
		}

		this.mGamePaused = true;

		this.mEngine.stop();
	}

	@Override
	protected void onDestroy() {
		if (BuildConfig.DEBUG) {
			Debug.d(this.getClass().getSimpleName() + ".onDestroy"
					+ " @(Thread: '" + Thread.currentThread().getName() + "')");
		}

		super.onDestroy();

		this.mEngine.onDestroy();

		try {
			this.onDestroyResources();
		} catch (final Throwable pThrowable) {
			Debug.e(this.getClass().getSimpleName()
					+ ".onDestroyResources failed." + " @(Thread: '"
					+ Thread.currentThread().getName() + "')", pThrowable);
		}

		this.onGameDestroyed();

		this.mEngine = null;
	}

	@Override
	public void onDestroyResources() throws Exception {
		if (BuildConfig.DEBUG) {
			Debug.d(this.getClass().getSimpleName() + ".onDestroyResources"
					+ " @(Thread: '" + Thread.currentThread().getName() + "')");
		}

		if (this.mEngine.getEngineOptions().getAudioOptions().needsMusic()) {
			this.getMusicManager().releaseAll();
		}

		if (this.mEngine.getEngineOptions().getAudioOptions().needsSound()) {
			this.getSoundManager().releaseAll();
		}
	}

	@Override
	public synchronized void onGameDestroyed() {
		if (BuildConfig.DEBUG) {
			Debug.d(this.getClass().getSimpleName() + ".onGameDestroyed"
					+ " @(Thread: '" + Thread.currentThread().getName() + "')");
		}

		this.mGameCreated = false;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public Engine getEngine() {
		return this.mEngine;
	}

	public boolean isGamePaused() {
		return this.mGamePaused;
	}

	public boolean isGameRunning() {
		return !this.mGamePaused;
	}

	public boolean isGameLoaded() {
		return this.mGameCreated;
	}

	public VertexBufferObjectManager getVertexBufferObjectManager() {
		return this.mEngine.getVertexBufferObjectManager();
	}

	public TextureManager getTextureManager() {
		return this.mEngine.getTextureManager();
	}

	public FontManager getFontManager() {
		return this.mEngine.getFontManager();
	}

	public ShaderProgramManager getShaderProgramManager() {
		return this.mEngine.getShaderProgramManager();
	}

	public SoundManager getSoundManager() {
		return this.mEngine.getSoundManager();
	}

	public MusicManager getMusicManager() {
		return this.mEngine.getMusicManager();
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	private void callGameResumedOnUIThread() {
		BaseGameActivity.this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				BaseGameActivity.this.onResumeGame();
			}
		});
	}

	protected void onSetContentView() {
		this.mRenderSurfaceView = new RenderSurfaceView(this);
		this.mRenderSurfaceView.setRenderer(this.mEngine, this);

		this.setContentView(this.mRenderSurfaceView,
				BaseGameActivity.createSurfaceViewLayoutParams());
	}

	/**
	 * @see Engine#runOnUpdateThread(Runnable)
	 */
	public void runOnUpdateThread(final Runnable pRunnable) {
		this.mEngine.runOnUpdateThread(pRunnable);
	}

	/**
	 * @see Engine#runOnUpdateThread(Runnable, boolean)
	 */
	public void runOnUpdateThread(final Runnable pRunnable,
			final boolean pOnlyWhenEngineRunning) {
		this.mEngine.runOnUpdateThread(pRunnable, pOnlyWhenEngineRunning);
	}

	private void acquireWakeLock() {
		this.acquireWakeLock(this.mEngine.getEngineOptions()
				.getWakeLockOptions());
	}

	private void acquireWakeLock(final WakeLockOptions pWakeLockOptions) {
		if (pWakeLockOptions == WakeLockOptions.SCREEN_ON) {
			ActivityUtils.keepScreenOn(this);
		} else {
			final PowerManager pm = (PowerManager) this
					.getSystemService(Context.POWER_SERVICE);
			this.mWakeLock = pm.newWakeLock(pWakeLockOptions.getFlag()
					| PowerManager.ON_AFTER_RELEASE, Constants.DEBUGTAG);
			try {
				this.mWakeLock.acquire();
			} catch (final SecurityException pSecurityException) {
				Debug.e("You have to add\n\t<uses-permission android:name=\"android.permission.WAKE_LOCK\"/>\nto your AndroidManifest.xml !",
						pSecurityException);
			}
		}
	}

	private void releaseWakeLock() {
		if (this.mWakeLock != null && this.mWakeLock.isHeld()) {
			this.mWakeLock.release();
		}
	}

	private void applyEngineOptions() {
		final EngineOptions engineOptions = this.mEngine.getEngineOptions();

		if (engineOptions.isFullscreen()) {
			ActivityUtils.requestFullscreen(this);
		}

		if (engineOptions.getAudioOptions().needsMusic()
				|| engineOptions.getAudioOptions().needsSound()) {
			this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		}

		switch (engineOptions.getScreenOrientation()) {
		case LANDSCAPE_FIXED:
			this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			break;
		case LANDSCAPE_SENSOR:
			if (SystemUtils.SDK_VERSION_GINGERBREAD_OR_LATER) {
				this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
			} else {
				Debug.w(ScreenOrientation.class.getSimpleName() + "."
						+ ScreenOrientation.LANDSCAPE_SENSOR
						+ " is not supported on this device. Falling back to "
						+ ScreenOrientation.class.getSimpleName() + "."
						+ ScreenOrientation.LANDSCAPE_FIXED);
				this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			}
			break;
		case PORTRAIT_FIXED:
			this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			break;
		case PORTRAIT_SENSOR:
			if (SystemUtils.SDK_VERSION_GINGERBREAD_OR_LATER) {
				this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
			} else {
				Debug.w(ScreenOrientation.class.getSimpleName() + "."
						+ ScreenOrientation.PORTRAIT_SENSOR
						+ " is not supported on this device. Falling back to "
						+ ScreenOrientation.class.getSimpleName() + "."
						+ ScreenOrientation.PORTRAIT_FIXED);
				this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			}
			break;
		}
	}

	protected static LayoutParams createSurfaceViewLayoutParams() {
		final LayoutParams layoutParams = new LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		layoutParams.gravity = Gravity.CENTER;
		return layoutParams;
	}

	protected void enableVibrator() {
		this.mEngine.enableVibrator(this);
	}

	/**
	 * @see {@link Engine#enableLocationSensor(Context, ILocationListener, LocationSensorOptions)}
	 */
	protected void enableLocationSensor(
			final ILocationListener pLocationListener,
			final LocationSensorOptions pLocationSensorOptions) {
		this.mEngine.enableLocationSensor(this, pLocationListener,
				pLocationSensorOptions);
	}

	/**
	 * @see {@link Engine#disableLocationSensor(Context)}
	 */
	protected void disableLocationSensor() {
		this.mEngine.disableLocationSensor(this);
	}

	/**
	 * @see {@link Engine#enableAccelerationSensor(Context, IAccelerationListener)}
	 */
	protected boolean enableAccelerationSensor(
			final IAccelerationListener pAccelerationListener) {
		return this.mEngine.enableAccelerationSensor(this,
				pAccelerationListener);
	}

	/**
	 * @see {@link Engine#enableAccelerationSensor(Context, IAccelerationListener, AccelerationSensorOptions)}
	 */
	protected boolean enableAccelerationSensor(
			final IAccelerationListener pAccelerationListener,
			final AccelerationSensorOptions pAccelerationSensorOptions) {
		return this.mEngine.enableAccelerationSensor(this,
				pAccelerationListener, pAccelerationSensorOptions);
	}

	/**
	 * @see {@link Engine#disableAccelerationSensor(Context)}
	 */
	protected boolean disableAccelerationSensor() {
		return this.mEngine.disableAccelerationSensor(this);
	}

	/**
	 * @see {@link Engine#enableOrientationSensor(Context, IOrientationListener)}
	 */
	protected boolean enableOrientationSensor(
			final IOrientationListener pOrientationListener) {
		return this.mEngine.enableOrientationSensor(this, pOrientationListener);
	}

	/**
	 * @see {@link Engine#enableOrientationSensor(Context, IOrientationListener, OrientationSensorOptions)}
	 */
	protected boolean enableOrientationSensor(
			final IOrientationListener pOrientationListener,
			final OrientationSensorOptions pLocationSensorOptions) {
		return this.mEngine.enableOrientationSensor(this, pOrientationListener,
				pLocationSensorOptions);
	}

	/**
	 * @see {@link Engine#disableOrientationSensor(Context)}
	 */
	protected boolean disableOrientationSensor() {
		return this.mEngine.disableOrientationSensor(this);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
