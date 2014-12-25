package com.orange.util.modifier.ease;


/**
 * (c) OrangeGame 2012
 * 
 *
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class EaseElasticInOut implements IEaseFunction {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private static EaseElasticInOut INSTANCE;

	// ===========================================================
	// Constructors
	// ===========================================================

	private EaseElasticInOut() {

	}

	public static EaseElasticInOut getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new EaseElasticInOut();
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
		final float percentage = pSecondsElapsed / pDuration;

		if(percentage < 0.5f) {
			return 0.5f * EaseElasticIn.getValue(2 * pSecondsElapsed, pDuration, 2 * percentage);
		} else {
			return 0.5f + 0.5f * EaseElasticOut.getValue(pSecondsElapsed * 2 - pDuration, pDuration, percentage * 2 - 1);
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
