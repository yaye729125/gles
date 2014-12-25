package com.orange.input.touch.detector;

import com.orange.input.touch.TouchEvent;

/**
 * (c) OrangeGame 2012
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class SurfaceScrollDetector extends ScrollDetector {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public SurfaceScrollDetector(final float pTriggerScrollMinimumDistance, final IScrollDetectorListener pScrollDetectorListener) {
		super(pTriggerScrollMinimumDistance, pScrollDetectorListener);
	}

	public SurfaceScrollDetector(final IScrollDetectorListener pScrollDetectorListener) {
		super(pScrollDetectorListener);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected float getX(final TouchEvent pTouchEvent) {
		return pTouchEvent.getMotionEvent().getX();
	}

	@Override
	protected float getY(final TouchEvent pTouchEvent) {
		return pTouchEvent.getMotionEvent().getY();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
