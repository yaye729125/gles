package com.orange.util.animationpack;

import com.orange.entity.sprite.AnimationData;
import com.orange.opengl.texture.ITexture;
import com.orange.opengl.texture.region.ITextureRegion;
import com.orange.opengl.texture.region.TiledTextureRegion;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class AnimationPackTiledTextureRegion extends TiledTextureRegion {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final String mAnimationName;
	private final AnimationData mAnimationData;

	// ===========================================================
	// Constructors
	// ===========================================================

	public AnimationPackTiledTextureRegion(final String pAnimationName, final long[] pFrameDurations, final int pLoopCount, final ITexture pTexture, final ITextureRegion ... pTextureRegions) {
		super(pTexture, pTextureRegions);

		this.mAnimationName = pAnimationName;
		final int frameCount = pFrameDurations.length;

		final int[] frames= new int[frameCount];
		for(int i = 0; i < frameCount; i++) {
			frames[i] = i;
		}

		this.mAnimationData = new AnimationData(pFrameDurations, frames, pLoopCount);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public String getAnimationName() {
		return this.mAnimationName;
	}

	public AnimationData getAnimationData() {
		return this.mAnimationData;
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
