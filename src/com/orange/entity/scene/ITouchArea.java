package com.orange.entity.scene;

import com.orange.input.touch.TouchEvent;
import com.orange.util.IMatcher;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface ITouchArea {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public boolean contains(final float pX, final float pY);

	public float[] convertSceneToLocalCoordinates(final float pX, final float pY);
	public float[] convertLocalToSceneCoordinates(final float pX, final float pY);

	public boolean onTouch(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY);
	
	/**
	 * This method only fires if this {@link ITouchArea} is registered to the {@link BaseScene} via {@link BaseScene#registerTouchArea(ITouchArea)}.
	 * @param pSceneTouchEvent
	 * @return <code>true</code> if the event was handled (that means {@link IOnAreaTouchListener} of the {@link BaseScene} will not be fired!), otherwise <code>false</code>.
	 */
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY);

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	public static interface ITouchAreaMatcher extends IMatcher<ITouchArea> {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================
	}
}