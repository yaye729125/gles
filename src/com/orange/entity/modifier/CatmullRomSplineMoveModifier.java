package com.orange.entity.modifier;

import com.orange.util.modifier.ease.IEaseFunction;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class CatmullRomSplineMoveModifier extends CardinalSplineMoveModifier {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public CatmullRomSplineMoveModifier(final float pDuration, final CatmullRomMoveModifierConfig pCatmullRomMoveModifierConfig) {
		super(pDuration, pCatmullRomMoveModifierConfig);
	}

	public CatmullRomSplineMoveModifier(final float pDuration, final CatmullRomMoveModifierConfig pCatmullRomMoveModifierConfig, final IEaseFunction pEaseFunction) {
		super(pDuration, pCatmullRomMoveModifierConfig, pEaseFunction);
	}

	public CatmullRomSplineMoveModifier(final float pDuration, final CatmullRomMoveModifierConfig pCatmullRomMoveModifierConfig, final IEntityModifierListener pEntityModifierListener) {
		super(pDuration, pCatmullRomMoveModifierConfig, pEntityModifierListener);
	}

	public CatmullRomSplineMoveModifier(final float pDuration, final CatmullRomMoveModifierConfig pCatmullRomMoveModifierConfig, final IEntityModifierListener pEntityModifierListener, final IEaseFunction pEaseFunction) {
		super(pDuration, pCatmullRomMoveModifierConfig, pEntityModifierListener, pEaseFunction);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	public class CatmullRomMoveModifierConfig extends CardinalSplineMoveModifierConfig {
		// ===========================================================
		// Constants
		// ===========================================================

		private static final int CARDINALSPLINE_CATMULLROM_TENSION = 0;

		// ===========================================================
		// Fields
		// ===========================================================

		// ===========================================================
		// Constructors
		// ===========================================================

		public CatmullRomMoveModifierConfig(final int pControlPointCount) {
			super(pControlPointCount, CatmullRomMoveModifierConfig.CARDINALSPLINE_CATMULLROM_TENSION);
		}

		// ===========================================================
		// Getter & Setter
		// ===========================================================

		// ===========================================================
		// Methods for/from SuperClass/Interfaces
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================

		// ===========================================================
		// Inner and Anonymous Classes
		// ===========================================================
	}
}
