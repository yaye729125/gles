package com.orange.util.texturepack;

import com.orange.opengl.texture.ITexture;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class TexturePack {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final ITexture mTexture;
	private final TexturePackTextureRegionLibrary mTexturePackTextureRegionLibrary;

	// ===========================================================
	// Constructors
	// ===========================================================

	public TexturePack(final ITexture pTexture, final TexturePackTextureRegionLibrary pTexturePackTextureRegionLibrary) {
		this.mTexture = pTexture;
		this.mTexturePackTextureRegionLibrary = pTexturePackTextureRegionLibrary;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public ITexture getTexture() {
		return this.mTexture;
	}

	public TexturePackTextureRegionLibrary getTexturePackTextureRegionLibrary() {
		return this.mTexturePackTextureRegionLibrary;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	public void loadTexture() {
		this.mTexture.load();
	}

	public void unloadTexture() {
		this.mTexture.unload();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}