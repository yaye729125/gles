package com.orange.opengl.view;

import com.orange.opengl.util.GLState;

/**
 * (c) OrangeGame 2012
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IRendererListener {
	// ===========================================================
	// Constants
	// ===========================================================

	public void onSurfaceCreated(final GLState pGlState);
	public void onSurfaceChanged(final GLState pGlState, final int pWidth, final int pHeight);

	// ===========================================================
	// Methods
	// ===========================================================
}