package com.orange.util.adt.io.in;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.Resources;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class ResourceInputStreamOpener implements IInputStreamOpener {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final Resources mResources;
	private final int mResourceID;

	// ===========================================================
	// Constructors
	// ===========================================================

	public ResourceInputStreamOpener(final Resources pResources, final int pResourceID) {
		this.mResources = pResources;
		this.mResourceID = pResourceID;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public InputStream open() throws IOException {
		return this.mResources.openRawResource(this.mResourceID);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
