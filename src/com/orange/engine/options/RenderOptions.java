package com.orange.engine.options;

/**
 * 渲染方式
 * (c) OrangeGame 2012
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class RenderOptions {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private boolean mMultiSampling = false;
	private boolean mDithering = false;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public boolean isMultiSampling() {
		return this.mMultiSampling;
	}

	public void setMultiSampling(final boolean pMultiSampling) {
		this.mMultiSampling = pMultiSampling;
	}

	public boolean isDithering() {
		return this.mDithering;
	}

	public void setDithering(final boolean pDithering) {
		this.mDithering = pDithering;
	}

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
