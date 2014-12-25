package com.orange.engine.options.resolutionpolicy;

import android.view.View.MeasureSpec;

/**
 * BaseResolutionPolicy:基础校验屏幕大小
 * (c) OrangeGame 2012 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public abstract class BaseResolutionPolicy implements IResolutionPolicy {
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

	protected static void throwOnNotMeasureSpecEXACTLY(final int pWidthMeasureSpec, final int pHeightMeasureSpec) {
		final int specWidthMode = MeasureSpec.getMode(pWidthMeasureSpec);
		final int specHeightMode = MeasureSpec.getMode(pHeightMeasureSpec);

		if (specWidthMode != MeasureSpec.EXACTLY || specHeightMode != MeasureSpec.EXACTLY) {
			throw new IllegalStateException("This IResolutionPolicy requires MeasureSpec.EXACTLY ! That means ");
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
