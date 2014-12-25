package com.orange.util.modifier.ease;

import android.util.FloatMath;

/**
 * (c) OrangeGame 2012
 * 
 *
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class EaseCircularIn implements IEaseFunction {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private static EaseCircularIn INSTANCE;

	// ===========================================================
	// Constructors
	// ===========================================================

	private EaseCircularIn() {

	}

	public static EaseCircularIn getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new EaseCircularIn();
		}
		return INSTANCE;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public float getPercentage(final float pSecondsElapsed, final float pDuration) {
		return EaseCircularIn.getValue(pSecondsElapsed / pDuration);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public static float getValue(final float pPercentage) {
		return -(FloatMath.sqrt(1 - pPercentage * pPercentage) - 1.0f);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
