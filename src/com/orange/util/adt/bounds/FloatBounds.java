package com.orange.util.adt.bounds;

import com.orange.util.adt.spatial.bounds.util.FloatBoundsUtils;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class FloatBounds implements IFloatBounds {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private float mXMin;
	private float mYMin;
	private float mXMax;
	private float mYMax;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public FloatBounds(final float pX, final float pY) {
		this.set(pX, pY);
	}

	public FloatBounds(final float pXMin, final float pYMin, final float pXMax, final float pYMax) {
		this.set(pXMin, pYMin, pXMax, pYMax);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	@Override
	public float getXMin() {
		return this.mXMin;
	}
	
	@Override
	public float getYMin() {
		return this.mYMin;
	}

	@Override
	public float getXMax() {
		return this.mXMax;
	}

	@Override
	public float getYMax() {
		return this.mYMax;
	}

	public void set(final float pX, final float pY) {
		this.set(pX, pY, pX, pY);
	}

	public void set(final float pXMin, final float pYMin, final float pXMax, final float pYMax) {
		this.mXMin = pXMin;
		this.mYMin = pYMin;
		this.mXMax = pXMax;
		this.mYMax = pYMax;

		if(pXMin > pXMax) {
			throw new IllegalArgumentException("pXMin: '" + pXMin + "' must be smaller or equal to pXMax: '" + pXMax + "'.");
		}
		if(pYMin > pYMax) {
			throw new IllegalArgumentException("pYMin: '" + pYMin + "' must be smaller or equal to pYMax: '" + pYMax + "'.");
		}
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	public boolean contains(final float pX, final float pY) {
		return FloatBoundsUtils.contains(this.mXMin, this.mYMin, this.mXMax, this.mYMax, pX, pY);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
