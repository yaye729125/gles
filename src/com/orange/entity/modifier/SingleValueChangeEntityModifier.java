package com.orange.entity.modifier;

import com.orange.entity.IEntity;
import com.orange.util.modifier.BaseSingleValueChangeModifier;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public abstract class SingleValueChangeEntityModifier extends BaseSingleValueChangeModifier<IEntity> implements IEntityModifier {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public SingleValueChangeEntityModifier(final float pDuration, final float pValueChange) {
		super(pDuration, pValueChange);
	}

	public SingleValueChangeEntityModifier(final float pDuration, final float pValueChange, final IEntityModifierListener pEntityModifierListener) {
		super(pDuration, pValueChange, pEntityModifierListener);
	}

	protected SingleValueChangeEntityModifier(final SingleValueChangeEntityModifier pSingleValueChangeEntityModifier) {
		super(pSingleValueChangeEntityModifier);
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
