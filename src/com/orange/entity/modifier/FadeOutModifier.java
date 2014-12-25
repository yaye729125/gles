package com.orange.entity.modifier;

import com.orange.util.modifier.ease.EaseLinear;
import com.orange.util.modifier.ease.IEaseFunction;


/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class FadeOutModifier extends AlphaModifier {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public FadeOutModifier(final float pDuration) {
		super(pDuration, 1.0f, 0.0f, EaseLinear.getInstance());
	}

	public FadeOutModifier(final float pDuration, final IEaseFunction pEaseFunction) {
		super(pDuration, 1.0f, 0.0f, pEaseFunction);
	}

	public FadeOutModifier(final float pDuration, final IEntityModifierListener pEntityModifierListener) {
		super(pDuration, 1.0f, 0.0f, pEntityModifierListener, EaseLinear.getInstance());
	}

	public FadeOutModifier(final float pDuration, final IEntityModifierListener pEntityModifierListener, final IEaseFunction pEaseFunction) {
		super(pDuration, 1.0f, 0.0f, pEntityModifierListener, pEaseFunction);
	}

	protected FadeOutModifier(final FadeOutModifier pFadeOutModifier) {
		super(pFadeOutModifier);
	}

	@Override
	public FadeOutModifier deepCopy() {
		return new FadeOutModifier(this);
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
