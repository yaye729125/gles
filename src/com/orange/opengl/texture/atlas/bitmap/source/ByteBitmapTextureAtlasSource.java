package com.orange.opengl.texture.atlas.bitmap.source;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;

import com.orange.opengl.texture.atlas.source.BaseTextureAtlasSource;

/**
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 *
 */
public class ByteBitmapTextureAtlasSource extends BaseTextureAtlasSource implements IBitmapTextureAtlasSource {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final byte[] mBytes;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public static ByteBitmapTextureAtlasSource create(final byte[] pBytes) {
		return ByteBitmapTextureAtlasSource.create(pBytes, 0, 0);
	}

	public static ByteBitmapTextureAtlasSource create(final byte[] pBytes, final int pTextureX, final int pTextureY) {
		final BitmapFactory.Options decodeOptions = new BitmapFactory.Options();
		decodeOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeByteArray(pBytes, 0, pBytes.length, decodeOptions);
		return new ByteBitmapTextureAtlasSource(pBytes, pTextureX, pTextureY, decodeOptions.outWidth, decodeOptions.outHeight);
	}

	ByteBitmapTextureAtlasSource(final byte[] pBytes, final int pTextureX, final int pTextureY, final int pTextureWidth, final int pTextureHeight) {
		super(pTextureX, pTextureY, pTextureWidth, pTextureHeight);

		this.mBytes = pBytes;
	}

	@Override
	public ByteBitmapTextureAtlasSource deepCopy() {
		return new ByteBitmapTextureAtlasSource(this.mBytes, this.mTextureX, this.mTextureY, this.mTextureWidth, this.mTextureHeight);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public Bitmap onLoadBitmap(final Config pBitmapConfig) {
		final BitmapFactory.Options decodeOptions = new BitmapFactory.Options();
		decodeOptions.inPreferredConfig = pBitmapConfig;
		return BitmapFactory.decodeByteArray(this.mBytes, 0, this.mBytes.length, decodeOptions);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "(" + this.mBytes + ")";
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}