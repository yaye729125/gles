package com.orange.engine.camera;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 相机工厂类
 * (c) OrangeGame 2012 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class CameraFactory {
	// ===========================================================
	// Constants
	// ===========================================================

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
	/**
	 * 创建像素相机
	 * @param pContext
	 * @param pCenterX
	 * @param pCenterY
	 * @return
	 */
	public static Camera createPixelPerfectCamera(final Context pContext, final float pCenterX, final float pCenterY) {
		final DisplayMetrics displayMetrics = CameraFactory.getDisplayMetrics(pContext);

		final float width = displayMetrics.widthPixels;
		final float height = displayMetrics.heightPixels;

		return new Camera(pCenterX - width * 0.5f, pCenterY - height * 0.5f, width, height);
	}

	/**
	 * 获取分辨率
	 * @param pContext
	 * @return
	 */
	private static DisplayMetrics getDisplayMetrics(final Context pContext) {
		return pContext.getResources().getDisplayMetrics();
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
