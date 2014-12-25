package com.orange.opengl.shader.source;

import com.orange.opengl.util.GLState;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IShaderSource {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public String getShaderSource(final GLState pGLState);
}
