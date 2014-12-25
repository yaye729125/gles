package com.orange.util.animationpack.exception;

import org.xml.sax.SAXException;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class AnimationPackParseException extends SAXException {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = 1136010869754861664L;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public AnimationPackParseException() {
		super();
	}

	public AnimationPackParseException(final String pDetailMessage) {
		super(pDetailMessage);
	}

	public AnimationPackParseException(final Exception pException) {
		super(pException);
	}

	public AnimationPackParseException(final String pMessage, final Exception pException) {
		super(pMessage, pException);
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
