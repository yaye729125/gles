package com.orange.util.modifier.ease;

/**
 * (c) OrangeGame 2012
 * 
 *
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class EaseBounceIn implements IEaseFunction {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private static EaseBounceIn INSTANCE;

	// ===========================================================
	// Constructors
	// ===========================================================

	private EaseBounceIn() {

	}

	public static EaseBounceIn getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new EaseBounceIn();
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
		return EaseBounceIn.getValue(pSecondsElapsed / pDuration);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public static float getValue(final float pPercentage) {
		// TODO Inline?
		return 1 - EaseBounceOut.getValue(1 - pPercentage);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
