package com.orange.opengl.shader.source;

import com.orange.opengl.util.GLState;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class StringShaderSource implements IShaderSource {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final String mShaderSource;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public StringShaderSource(final String pShaderSource) {
		this.mShaderSource = pShaderSource;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	@Override
	public String getShaderSource(final GLState pGLState) {
		return this.mShaderSource;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
