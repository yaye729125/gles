package com.orange.opengl.shader.exception;
/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class ShaderProgramLinkException extends ShaderProgramException {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = 5418521931032742504L;
	
	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public ShaderProgramLinkException(final String pMessage) {
		super(pMessage);
	}

	public ShaderProgramLinkException(final String pMessage, final ShaderProgramException pShaderProgramException) {
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
