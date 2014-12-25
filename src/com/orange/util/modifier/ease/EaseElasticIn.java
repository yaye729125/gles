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
public class EaseElasticIn implements IEaseFunction {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private static EaseElasticIn INSTANCE;

	// ===========================================================
	// Constructors
	// ===========================================================

	private EaseElasticIn() {

	}

	public static EaseElasticIn getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new EaseElasticIn();
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
		return EaseElasticIn.getValue(pSecondsElapsed, pDuration, pSecondsElapsed / pDuration);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public static float getValue(final float pSecondsElapsed, final float pDuration, final float pPercentage) {
		if(pSecondsElapsed == 0) {
			return 0;
		}
		if(pSecondsElapsed == pDuration) {
			return 1;
		}

		final float p = pDuration * 0.3f;
		final float s = p / 4;

		final float t = pPercentage - 1;
		return -(float)Math.pow(2, 10 * t) * FloatMath.sin((t * pDuration - s) * MathConstants.PI_TWICE / p);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
