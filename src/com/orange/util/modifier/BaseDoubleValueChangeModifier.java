package com.orange.util.modifier;


/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public abstract class BaseDoubleValueChangeModifier<T> extends BaseSingleValueChangeModifier<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private float mValueChangeBPerSecond;

	// ===========================================================
	// Constructors
	// ===========================================================

	public BaseDoubleValueChangeModifier(final float pDuration, final float pValueChangeA, final float pValueChangeB) {
		this(pDuration, pValueChangeA, pValueChangeB, null);
	}

	public BaseDoubleValueChangeModifier(final float pDuration, final float pValueChangeA, final float pValueChangeB, final IModifierListener<T> pModifierListener) {
		super(pDuration, pValueChangeA, pModifierListener);

		this.mValueChangeBPerSecond = pValueChangeB / pDuration;
	}

	protected BaseDoubleValueChangeModifier(final BaseDoubleValueChangeModifier<T> pBaseDoubleValueChangeModifier) {
		super(pBaseDoubleValueChangeModifier);

		this.mValueChangeBPerSecond = pBaseDoubleValueChangeModifier.mValueChangeBPerSecond;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void onChangeValue(final float pSecondsElapsed, final T pItem, final float pValueA) {
		this.onChangeValues(pSecondsElapsed, pItem, pValueA, pSecondsElapsed * this.mValueChangeBPerSecond);
	}

	protected abstract void onChangeValues(float pSecondsElapsed, T pItem, float pValueA, float pValueB);

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
