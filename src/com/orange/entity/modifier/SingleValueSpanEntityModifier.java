package com.orange.entity.modifier;

import com.orange.entity.IEntity;
import com.orange.util.modifier.BaseSingleValueSpanModifier;
import com.orange.util.modifier.ease.IEaseFunction;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public abstract class SingleValueSpanEntityModifier extends BaseSingleValueSpanModifier<IEntity> implements IEntityModifier {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public SingleValueSpanEntityModifier(final float pDuration, final float pFromValue, final float pToValue) {
		super(pDuration, pFromValue, pToValue);
	}

	public SingleValueSpanEntityModifier(final float pDuration, final float pFromValue, final float pToValue, final IEaseFunction pEaseFunction) {
		super(pDuration, pFromValue, pToValue, pEaseFunction);
	}

	public SingleValueSpanEntityModifier(final float pDuration, final float pFromValue, final float pToValue, final IEntityModifierListener pEntityModifierListener) {
		super(pDuration, pFromValue, pToValue, pEntityModifierListener);
	}

	public SingleValueSpanEntityModifier(final float pDuration, final float pFromValue, final float pToValue, final IEntityModifierListener pEntityModifierListener, final IEaseFunction pEaseFunction) {
		super(pDuration, pFromValue, pToValue, pEntityModifierListener, pEaseFunction);
	}

	protected SingleValueSpanEntityModifier(final SingleValueSpanEntityModifier pSingleValueSpanEntityModifier) {
		super(pSingleValueSpanEntityModifier);
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
