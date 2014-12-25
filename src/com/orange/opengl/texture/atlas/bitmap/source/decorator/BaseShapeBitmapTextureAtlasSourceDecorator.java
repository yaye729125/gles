package com.orange.opengl.texture.atlas.bitmap.source.decorator;

import com.orange.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import com.orange.opengl.texture.atlas.bitmap.source.decorator.shape.IBitmapTextureAtlasSourceDecoratorShape;

import android.graphics.Canvas;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public abstract class BaseShapeBitmapTextureAtlasSourceDecorator extends BaseBitmapTextureAtlasSourceDecorator {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	protected final IBitmapTextureAtlasSourceDecoratorShape mBitmapTextureAtlasSourceDecoratorShape;

	// ===========================================================
	// Constructors
	// ===========================================================

	public BaseShapeBitmapTextureAtlasSourceDecorator(final IBitmapTextureAtlasSource pBitmapTextureAtlasSource, final IBitmapTextureAtlasSourceDecoratorShape pBitmapTextureAtlasSourceDecoratorShape, final TextureAtlasSourceDecoratorOptions pTextureAtlasSourceDecoratorOptions) {
		super(pBitmapTextureAtlasSource, pTextureAtlasSourceDecoratorOptions);

		this.mBitmapTextureAtlasSourceDecoratorShape = pBitmapTextureAtlasSourceDecoratorShape;
	}

	@Override
	public abstract BaseShapeBitmapTextureAtlasSourceDecorator deepCopy();

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void onDecorateBitmap(final Canvas pCanvas){
		this.mBitmapTextureAtlasSourceDecoratorShape.onDecorateBitmap(pCanvas, this.mPaint, (this.mTextureAtlasSourceDecoratorOptions == null) ? TextureAtlasSourceDecoratorOptions.DEFAULT : this.mTextureAtlasSourceDecoratorOptions);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
