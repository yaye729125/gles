package com.orange.opengl.texture.region;

import com.orange.opengl.texture.ITexture;

/**
 * (c) OrangeGame 2012
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public abstract class BaseTextureRegion implements ITextureRegion {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	protected final ITexture mTexture;

	// ===========================================================
	// Constructors
	// ===========================================================

	public BaseTextureRegion(final ITexture pTexture) {
		this.mTexture = pTexture;
	}
	
	@Override
	public abstract ITextureRegion deepCopy();

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public ITexture getTexture() {
		return this.mTexture;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
