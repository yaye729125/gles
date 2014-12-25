package com.orange.util.modifier.ease;

/**
 * (c) OrangeGame 2012
 * 
 *
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class EaseQuartIn implements IEaseFunction {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private static EaseQuartIn INSTANCE;

	// ===========================================================
	// Constructors
	// ===========================================================

	private EaseQuartIn() {

	}

	public static EaseQuartIn getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new EaseQuartIn();
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
		return EaseQuartIn.getValue(pSecondsElapsed / pDuration);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public static float getValue(final float pPercentage) {
		return pPercentage * pPercentage * pPercentage * pPercentage;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
