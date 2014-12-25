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
public class EaseElasticOut implements IEaseFunction {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private static EaseElasticOut INSTANCE;

	// ===========================================================
	// Constructors
	// ===========================================================

	private EaseElasticOut() {

	}

	public static EaseElasticOut getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new EaseElasticOut();
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
		return EaseElasticOut.getValue(pSecondsElapsed, pDuration, pSecondsElapsed / pDuration);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public static float getValue(final float pSecondsElapsed, final float pDuration, final float pPercentageDone) {
		if(pSecondsElapsed == 0) {
			return 0;
		}
		if(pSecondsElapsed == pDuration) {
			return 1;
		}

		final float p = pDuration * 0.3f;
		final float s = p / 4;

		return 1 + (float)Math.pow(2, -10 * pPercentageDone) * FloatMath.sin((pPercentageDone * pDuration - s) * MathConstants.PI_TWICE / p);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
