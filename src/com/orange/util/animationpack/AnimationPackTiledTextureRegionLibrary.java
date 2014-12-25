package com.orange.util.animationpack;

import java.util.HashMap;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class AnimationPackTiledTextureRegionLibrary {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final HashMap<String, AnimationPackTiledTextureRegion> mAnimationPackTiledTextureRegionMapping = new HashMap<String, AnimationPackTiledTextureRegion>();

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void put(final AnimationPackTiledTextureRegion pAnimationPackTiledTextureRegion) {
		this.mAnimationPackTiledTextureRegionMapping.put(pAnimationPackTiledTextureRegion.getAnimationName(), pAnimationPackTiledTextureRegion);
	}

	public AnimationPackTiledTextureRegion get(final String pAnimationName) {
		return this.mAnimationPackTiledTextureRegionMapping.get(pAnimationName);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
