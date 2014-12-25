package com.orange.util.modifier.util;

import com.orange.util.modifier.IModifier;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class ModifierUtils {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public static float getSequenceDurationOfModifier(final IModifier<?>[] pModifiers){
		float duration = Float.MIN_VALUE;

		for(int i = pModifiers.length - 1; i >= 0; i--) {
			duration += pModifiers[i].getDuration();
		}

		return duration;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
