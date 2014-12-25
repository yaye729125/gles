package com.orange.opengl.shader.exception;

import com.orange.util.exception.AndEngineRuntimeException;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class ShaderProgramException extends AndEngineRuntimeException {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = 2377158902169370516L;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public ShaderProgramException(final String pMessage) {
		super(pMessage);
	}

	public ShaderProgramException(final String pMessage, final ShaderProgramException pShaderProgramException) {
		super(pMessage, pShaderProgramException);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

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