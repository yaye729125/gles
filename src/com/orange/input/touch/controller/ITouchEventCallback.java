package com.orange.input.touch.controller;

import com.orange.input.touch.TouchEvent;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface ITouchEventCallback {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public boolean onTouchEvent(final TouchEvent pTouchEvent);
}
