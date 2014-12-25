package com.orange.util.texturepack;

import java.util.HashMap;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class TexturePackLibrary {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final HashMap<String, TexturePack> mTexturePackMapping = new HashMap<String, TexturePack>();
	private final HashMap<String, TexturePackTextureRegion> mTexturePackTextureRegionSourceMapping = new HashMap<String, TexturePackTextureRegion>();

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	public void put(final String pID, final TexturePack pTexturePack) {
		this.mTexturePackMapping.put(pID, pTexturePack);
		this.mTexturePackTextureRegionSourceMapping.putAll(pTexturePack.getTexturePackTextureRegionLibrary().getSourceMapping());
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public TexturePackTextureRegion getTexturePackTextureRegion(final String pTexturePackTextureRegionSource) {
		return this.mTexturePackTextureRegionSourceMapping.get(pTexturePackTextureRegionSource);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}