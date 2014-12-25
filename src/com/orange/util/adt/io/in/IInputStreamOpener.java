package com.orange.util.adt.io.in;

import java.io.IOException;
import java.io.InputStream;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IInputStreamOpener {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public InputStream open() throws IOException;
}

