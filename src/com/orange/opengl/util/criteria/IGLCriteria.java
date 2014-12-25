package com.orange.opengl.util.criteria;

import com.orange.opengl.util.GLState;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IGLCriteria {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	public boolean isMet(final GLState pGLState);

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}