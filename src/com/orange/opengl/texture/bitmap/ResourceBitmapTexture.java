package com.orange.opengl.texture.bitmap;

import java.io.IOException;

import com.orange.opengl.texture.ITextureStateListener;
import com.orange.opengl.texture.TextureManager;
import com.orange.opengl.texture.TextureOptions;
import com.orange.util.adt.io.in.ResourceInputStreamOpener;

import android.content.res.Resources;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class ResourceBitmapTexture extends BitmapTexture {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public ResourceBitmapTexture(final TextureManager pTextureManager, final Resources pResources, final int pDrawableResourceID) throws IOException {
		super(pTextureManager, new ResourceInputStreamOpener(pResources, pDrawableResourceID));
	}

	public ResourceBitmapTexture(final TextureManager pTextureManager, final Resources pResources, final int pDrawableResourceID, final BitmapTextureFormat pBitmapTextureFormat) throws IOException {
		super(pTextureManager, new ResourceInputStreamOpener(pResources, pDrawableResourceID), pBitmapTextureFormat);
	}

	public ResourceBitmapTexture(final TextureManager pTextureManager, final Resources pResources, final int pDrawableResourceID, final TextureOptions pTextureOptions) throws IOException {
		super(pTextureManager, new ResourceInputStreamOpener(pResources, pDrawableResourceID), pTextureOptions);
	}

	public ResourceBitmapTexture(final TextureManager pTextureManager, final Resources pResources, final int pDrawableResourceID, final BitmapTextureFormat pBitmapTextureFormat, final TextureOptions pTextureOptions) throws IOException {
		super(pTextureManager, new ResourceInputStreamOpener(pResources, pDrawableResourceID), pBitmapTextureFormat, pTextureOptions);
	}

	public ResourceBitmapTexture(final TextureManager pTextureManager, final Resources pResources, final int pDrawableResourceID, final BitmapTextureFormat pBitmapTextureFormat, final TextureOptions pTextureOptions, final ITextureStateListener pTextureStateListener) throws IOException {
		super(pTextureManager, new ResourceInputStreamOpener(pResources, pDrawableResourceID), pBitmapTextureFormat, pTextureOptions, pTextureStateListener);
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
