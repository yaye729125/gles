package com.orange.engine.options;

import android.content.Context;
import android.util.DisplayMetrics;

import com.orange.engine.camera.Camera;
import com.orange.engine.options.resolutionpolicy.FillResolutionPolicy;
import com.orange.util.DisplayUtils;
import com.orange.util.ReflectionUtils;
/**
 * 供应用层设置引擎相关参数，处理全屏适配等
 * (c) OrangeGame 2012
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class PixelPerfectEngineOptions {

	public static final float DEFAULT_DESIRED_SIZE = 800;

	private Context mContext;
	private Class<? extends Camera> mCameraCls;
	private ScreenOrientation mScreenOrientation = ScreenOrientation.LANDSCAPE_FIXED;
	private PixelPerfectMode mPixelPerfectMode = PixelPerfectMode.CHANGE_WIDTH;
	private float mDesiredSize = DEFAULT_DESIRED_SIZE;

	public PixelPerfectEngineOptions(Context pContext,
			Class<? extends Camera> pCameraCls) {
		this.mContext = pContext;
		this.mCameraCls = pCameraCls;
	}

	public EngineOptions createEngineOptions() {
		float[] screenSize = this.getScreenSize();
		float screenWidth = screenSize[0];
		float screenHeight = screenSize[1];
		float screenRatio = screenWidth / screenHeight;

		float cameraWidth = this.mDesiredSize;
		float cameraHeight = this.mDesiredSize;
		if (this.mPixelPerfectMode == PixelPerfectMode.CHANGE_WIDTH) {
			cameraWidth = this.mDesiredSize * screenRatio;
		} else if (this.mPixelPerfectMode == PixelPerfectMode.CHANGE_HEIGHT) {
			cameraHeight = this.mDesiredSize / screenRatio;
		}
		Class<?>[] parameterTypes = { float.class, float.class, float.class,
				float.class };
		Object[] argParam = { 0.0f, 0.0f, cameraWidth, cameraHeight };
		Camera camera = ReflectionUtils.newInstance(this.mCameraCls,
				parameterTypes, argParam);
		EngineOptions engineOptions = new EngineOptions(true,
				this.mScreenOrientation, new FillResolutionPolicy(), camera);
		engineOptions.getTouchOptions().setNeedsMultiTouch(true);
		engineOptions.getAudioOptions().setNeedsSound(true);
		engineOptions.getAudioOptions().setNeedsMusic(true);
		engineOptions.getRenderOptions().setDithering(true);
		return engineOptions;
	}

	public void changeDensity() {
		DisplayMetrics displayMetrics = this.mContext.getResources()
				.getDisplayMetrics();
	}

	/**
	 * 获取屏幕大小
	 * 
	 * @return float[0] 宽 ， float[1]高
	 */
	private float[] getScreenSize() {
		int[] screenSize = DisplayUtils.getScreenSize(this.mContext);
		float screenWidth = screenSize[0];
		float screenHeight = screenSize[1];
		// 做宽高长度判断，避免出现计算错误的情况
		if (this.mScreenOrientation == ScreenOrientation.LANDSCAPE_FIXED) {
			if (screenSize[0] > screenSize[1]) {
				screenWidth = screenSize[0];
				screenHeight = screenSize[1];
			} else {
				screenWidth = screenSize[1];
				screenHeight = screenSize[0];
			}
		} else if (this.mScreenOrientation == ScreenOrientation.PORTRAIT_FIXED) {
			if (screenSize[0] < screenSize[1]) {
				screenWidth = screenSize[0];
				screenHeight = screenSize[1];
			} else {
				screenWidth = screenSize[1];
				screenHeight = screenSize[0];
			}
		}
		return new float[] { screenWidth, screenHeight };
	}

	/**
	 * 参考尺寸，默认800
	 * 
	 * @param mDesiredSize
	 */
	public void setDesiredSize(float mDesiredSize) {
		this.mDesiredSize = mDesiredSize;
	}

	/**
	 * 屏幕方向,默认横屏
	 * 
	 * @param mScreenOrientation
	 */
	public void setScreenOrientation(ScreenOrientation mScreenOrientation) {
		this.mScreenOrientation = mScreenOrientation;
	}

	/**
	 * 适配模式，默认改变宽
	 * 
	 * @param mPixelPerfectMode
	 */
	public void setPixelPerfectMode(PixelPerfectMode mPixelPerfectMode) {
		this.mPixelPerfectMode = mPixelPerfectMode;
	}

}
