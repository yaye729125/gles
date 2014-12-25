package com.orange.engine.options;

import com.orange.engine.Engine.EngineLock;
import com.orange.engine.Engine.UpdateThread;
import com.orange.engine.camera.Camera;
import com.orange.engine.options.resolutionpolicy.IResolutionPolicy;

/**
 * 引擎相关选项:可设置横竖屏、适配分式、声音（是否开启）、触摸事件（是否多点触控）、渲染方式等
 * (c) OrangeGame 2012
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class EngineOptions {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private EngineLock mEngineLock;

	private final boolean mFullscreen;
	private final ScreenOrientation mScreenOrientation;
	private final IResolutionPolicy mResolutionPolicy;
	private final Camera mCamera;

	private final TouchOptions mTouchOptions = new TouchOptions();
	private final AudioOptions mAudioOptions = new AudioOptions();
	private final RenderOptions mRenderOptions = new RenderOptions();

	private WakeLockOptions mWakeLockOptions = WakeLockOptions.SCREEN_ON;

	private UpdateThread mUpdateThread;
	private int mUpdateThreadPriority = android.os.Process.THREAD_PRIORITY_DEFAULT;

	// ===========================================================
	// Constructors
	// ===========================================================

	public EngineOptions(final boolean pFullscreen, final ScreenOrientation pScreenOrientation, final IResolutionPolicy pResolutionPolicy, final Camera pCamera) {
		this.mFullscreen = pFullscreen;
		this.mScreenOrientation = pScreenOrientation;
		this.mResolutionPolicy = pResolutionPolicy;
		this.mCamera = pCamera;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public boolean hasEngineLock() {
		return this.mEngineLock != null;
	}

	public EngineLock getEngineLock() {
		return this.mEngineLock;
	}

	public void setEngineLock(final EngineLock pEngineLock) {
		this.mEngineLock = pEngineLock;
	}

	public TouchOptions getTouchOptions() {
		return this.mTouchOptions;
	}

	public AudioOptions getAudioOptions() {
		return this.mAudioOptions;
	}

	public RenderOptions getRenderOptions() {
		return this.mRenderOptions;
	}

	public boolean isFullscreen() {
		return this.mFullscreen;
	}

	public ScreenOrientation getScreenOrientation() {
		return this.mScreenOrientation;
	}

	public IResolutionPolicy getResolutionPolicy() {
		return this.mResolutionPolicy;
	}

	public Camera getCamera() {
		return this.mCamera;
	}

	public boolean hasUpdateThread() {
		return this.mUpdateThread != null;
	}

	public UpdateThread getUpdateThread() {
		return this.mUpdateThread;
	}

	public void setUpdateThread(final UpdateThread pUpdateThread) {
		this.mUpdateThread = pUpdateThread;
	}

	public int getUpdateThreadPriority() {
		return this.mUpdateThreadPriority;
	}

	/**
	 * @param pUpdateThreadPriority Use constants from: {@link android.os.Process}.
	 */
	public void setUpdateThreadPriority(final int pUpdateThreadPriority) {
		this.mUpdateThreadPriority = pUpdateThreadPriority;
	}

	public WakeLockOptions getWakeLockOptions() {
		return this.mWakeLockOptions;
	}

	public EngineOptions setWakeLockOptions(final WakeLockOptions pWakeLockOptions) {
		this.mWakeLockOptions = pWakeLockOptions;
		return this;
	}

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