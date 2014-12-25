package com.orange.util.modifier.ease;

/**
 * (c) OrangeGame 2012
 * 
 *
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class EaseQuadIn implements IEaseFunction {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private static EaseQuadIn INSTANCE;

	// ===========================================================
	// Constructors
	// ===========================================================

	private EaseQuadIn() {

	}

	public static EaseQuadIn getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new EaseQuadIn();
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
		return EaseQuadIn.getValue(pSecondsElapsed / pDuration);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public static float getValue(final float pPercentage) {
		return pPercentage * pPercentage;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
