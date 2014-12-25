package com.orange.entity.particle.emitter;

import static com.orange.util.Constants.VERTEX_INDEX_X;
import static com.orange.util.Constants.VERTEX_INDEX_Y;

import com.orange.util.math.MathUtils;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class RectangleParticleEmitter extends BaseRectangleParticleEmitter {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public RectangleParticleEmitter(final float pCenterX, final float pCenterY, final float pWidth, final float pHeight) {
		super(pCenterX, pCenterY, pWidth, pHeight);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void getPositionOffset(final float[] pOffset) {
		pOffset[VERTEX_INDEX_X] = this.mCenterX - this.mWidthHalf + MathUtils.RANDOM.nextFloat() * this.mWidth;
		pOffset[VERTEX_INDEX_Y] = this.mCenterY - this.mHeightHalf + MathUtils.RANDOM.nextFloat() * this.mHeight;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
