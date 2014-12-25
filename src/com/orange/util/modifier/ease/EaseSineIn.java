package com.orange.util.modifier.ease;

import com.orange.util.math.MathConstants;

import android.util.FloatMath;

/**
 * (c) OrangeGame 2012
 * 
 *
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class EaseSineIn implements IEaseFunction {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private static EaseSineIn INSTANCE;

	// ===========================================================
	// Constructors
	// ===========================================================

	private EaseSineIn() {

	}

	public static EaseSineIn getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new EaseSineIn();
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
		return EaseSineIn.getValue(pSecondsElapsed / pDuration);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public static float getValue(final float pPercentage) {
		return -FloatMath.cos(pPercentage * MathConstants.PI_HALF) + 1;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
