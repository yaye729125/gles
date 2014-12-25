package com.orange.util.animationpack;

import com.orange.util.texturepack.TexturePackLibrary;


/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class AnimationPack {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final TexturePackLibrary mTexturePackLibrary;
	private final AnimationPackTiledTextureRegionLibrary mAnimationPackTiledTextureRegionLibrary;

	// ===========================================================
	// Constructors
	// ===========================================================

	public AnimationPack(final TexturePackLibrary pTexturePackLibrary, final AnimationPackTiledTextureRegionLibrary pAnimationPackTiledTextureRegionLibrary) {
		this.mTexturePackLibrary = pTexturePackLibrary;
		this.mAnimationPackTiledTextureRegionLibrary = pAnimationPackTiledTextureRegionLibrary;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public TexturePackLibrary getTexturePackLibrary() {
		return this.mTexturePackLibrary;
	}

	public AnimationPackTiledTextureRegionLibrary getAnimationPackAnimationDataLibrary() {
		return this.mAnimationPackTiledTextureRegionLibrary;
	}

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
