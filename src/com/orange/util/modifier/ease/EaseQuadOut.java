package com.orange.util.modifier.ease;

/**
 * (c) OrangeGame 2012
 * 
 *
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class EaseQuadOut implements IEaseFunction {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private static EaseQuadOut INSTANCE;

	// ===========================================================
	// Constructors
	// ===========================================================

	private EaseQuadOut() {

	}

	public static EaseQuadOut getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new EaseQuadOut();
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
		return EaseQuadOut.getValue(pSecondsElapsed / pDuration);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public static float getValue(final float pPercentage) {
		return -pPercentage * (pPercentage - 2);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
