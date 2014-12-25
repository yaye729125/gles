package com.orange.entity.modifier;

import com.orange.entity.IEntity;
import com.orange.util.modifier.ease.EaseLinear;
import com.orange.util.modifier.ease.IEaseFunction;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class SkewXModifier extends SingleValueSpanEntityModifier {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public SkewXModifier(final float pDuration, final float pFromSkewX, final float pToSkewX) {
		this(pDuration, pFromSkewX, pToSkewX, null, EaseLinear.getInstance());
	}

	public SkewXModifier(final float pDuration, final float pFromSkewX, final float pToSkewX, final IEaseFunction pEaseFunction) {
		this(pDuration, pFromSkewX, pToSkewX, null, pEaseFunction);
	}

	public SkewXModifier(final float pDuration, final float pFromSkewX, final float pToSkewX, final IEntityModifierListener pEntityModifierListener) {
		super(pDuration, pFromSkewX, pToSkewX, pEntityModifierListener, EaseLinear.getInstance());
	}

	public SkewXModifier(final float pDuration, final float pFromSkewX, final float pToSkewX, final IEntityModifierListener pEntityModifierListener, final IEaseFunction pEaseFunction) {
		super(pDuration, pFromSkewX, pToSkewX, pEntityModifierListener, pEaseFunction);
	}

	protected SkewXModifier(final SkewXModifier pSkewXModifier) {
		super(pSkewXModifier);
	}

	@Override
	public SkewXModifier deepCopy(){
		return new SkewXModifier(this);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void onSetInitialValue(final IEntity pEntity, final float pSkewX) {
		pEntity.setSkewX(pSkewX);
	}

	@Override
	protected void onSetValue(final IEntity pEntity, final float pPercentageDone, final float pSkewX) {
		pEntity.setSkewX(pSkewX);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
