package com.orange.opengl.texture.atlas.bitmap.source;

import com.orange.opengl.texture.atlas.source.ITextureAtlasSource;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IBitmapTextureAtlasSource extends ITextureAtlasSource {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	@Override
	public IBitmapTextureAtlasSource deepCopy();

	public Bitmap onLoadBitmap(final Config pBitmapConfig);
}