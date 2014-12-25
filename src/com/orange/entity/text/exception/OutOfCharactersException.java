package com.orange.entity.text.exception;


/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class OutOfCharactersException extends TextException {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = 3076821980884912905L;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public OutOfCharactersException() {
		super();
	}

	public OutOfCharactersException(final String pMessage) {
		super(pMessage);
	}

	public OutOfCharactersException(final Throwable pThrowable) {
		super(pThrowable);
	}

	public OutOfCharactersException(final String pMessage, final Throwable pThrowable) {
		super(pMessage, pThrowable);
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
