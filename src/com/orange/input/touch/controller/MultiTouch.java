package com.orange.input.touch.controller;

import com.orange.util.system.SystemUtils;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * (c) OrangeGame 2012
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class MultiTouch {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private static Boolean SUPPORTED = null;
	private static Boolean SUPPORTED_DISTINCT = null;

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

	public static boolean isSupported(final Context pContext) {
		if(MultiTouch.SUPPORTED == null) {
			MultiTouch.SUPPORTED = SystemUtils.hasSystemFeature(pContext, PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH);
		}

		return MultiTouch.SUPPORTED;
	}

	public static boolean isSupportedDistinct(final Context pContext) {
		if(MultiTouch.SUPPORTED_DISTINCT == null) {
			MultiTouch.SUPPORTED_DISTINCT = SystemUtils.hasSystemFeature(pContext, PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH_DISTINCT);
		}

		return MultiTouch.SUPPORTED_DISTINCT;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
