package com.orange.engine.handler;

import com.orange.util.IMatcher;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IUpdateHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	/**
	 * 更新
	 * @param pSecondsElapsed
	 */
	public void onUpdate(final float pSecondsElapsed);
	/**重置*/
	public void reset();
	
	// TODO Maybe add onRegister and onUnregister. (Maybe add SimpleUpdateHandler that implements all methods, but onUpdate)

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	
	public interface IUpdateHandlerMatcher extends IMatcher<IUpdateHandler> {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================
	}
}
