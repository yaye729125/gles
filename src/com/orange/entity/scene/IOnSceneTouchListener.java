package com.orange.entity.scene;

import com.orange.input.touch.TouchEvent;

/**
 * An interface for a callback to be invoked when a {@link TouchEvent} is
 * dispatched to a {@link BaseScene}. The callback will be invoked
 * after all {@link ITouchArea}s have been checked and none consumed the {@link TouchEvent}.
 *
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IOnSceneTouchListener {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * Called when a {@link TouchEvent} is dispatched to a {@link BaseScene}.
	 * 
	 * @param pScene The {@link BaseScene} that the {@link TouchEvent} has been dispatched to.
	 * @param pSceneTouchEvent The {@link TouchEvent} object containing full information about the event.
	 * 
	 * @return <code>true</code> if this {@link IOnSceneTouchListener} has consumed the {@link TouchEvent}, <code>false</code> otherwise.
	 */
	public boolean onSceneTouchEvent(final BaseScene pScene, final TouchEvent pSceneTouchEvent);
}