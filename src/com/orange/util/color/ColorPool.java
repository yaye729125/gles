package com.orange.util.color;

import com.orange.util.adt.pool.GenericPool;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class ColorPool extends GenericPool<Color> {
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

	@Override
	protected Color onAllocatePoolItem() {
		return new Color(Color.WHITE);
	}

	@Override
	protected void onHandleRecycleItem(final Color pColor) {
		pColor.setChecking(Color.WHITE);

		super.onHandleRecycleItem(pColor);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
