package com.orange.opengl.texture.bitmap;

import java.io.IOException;

import com.orange.opengl.texture.ITextureStateListener;
import com.orange.opengl.texture.TextureManager;
import com.orange.opengl.texture.TextureOptions;
import com.orange.util.adt.io.in.AssetInputStreamOpener;

import android.content.res.AssetManager;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class AssetBitmapTexture extends BitmapTexture {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public AssetBitmapTexture(final TextureManager pTextureManager, final AssetManager pAssetManager, final String pAssetPath) throws IOException {
		super(pTextureManager, new AssetInputStreamOpener(pAssetManager, pAssetPath));
	}

	public AssetBitmapTexture(final TextureManager pTextureManager, final AssetManager pAssetManager, final String pAssetPath, final BitmapTextureFormat pBitmapTextureFormat) throws IOException {
		super(pTextureManager, new AssetInputStreamOpener(pAssetManager, pAssetPath), pBitmapTextureFormat);
	}

	public AssetBitmapTexture(final TextureManager pTextureManager, final AssetManager pAssetManager, final String pAssetPath, final TextureOptions pTextureOptions) throws IOException {
		super(pTextureManager, new AssetInputStreamOpener(pAssetManager, pAssetPath), pTextureOptions);
	}

	public AssetBitmapTexture(final TextureManager pTextureManager, final AssetManager pAssetManager, final String pAssetPath, final BitmapTextureFormat pBitmapTextureFormat, final TextureOptions pTextureOptions) throws IOException {
		super(pTextureManager, new AssetInputStreamOpener(pAssetManager, pAssetPath), pBitmapTextureFormat, pTextureOptions);
	}

	public AssetBitmapTexture(final TextureManager pTextureManager, final AssetManager pAssetManager, final String pAssetPath, final BitmapTextureFormat pBitmapTextureFormat, final TextureOptions pTextureOptions, final ITextureStateListener pTextureStateListener) throws IOException {
		super(pTextureManager, new AssetInputStreamOpener(pAssetManager, pAssetPath), pBitmapTextureFormat, pTextureOptions, pTextureStateListener);
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
