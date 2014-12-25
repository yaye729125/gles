package com.orange.entity.particle.emitter;

import static com.orange.util.Constants.VERTEX_INDEX_X;
import static com.orange.util.Constants.VERTEX_INDEX_Y;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class PointParticleEmitter extends BaseParticleEmitter {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * 点粒子发射器
	 * @param pCenterX 发射中心点X
	 * @param pCenterY 发射中心点Y*/
	public PointParticleEmitter(final float pCenterX, final float pCenterY) {
		super(pCenterX, pCenterY);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void getPositionOffset(final float[] pOffset) {
		pOffset[VERTEX_INDEX_X] = this.mCenterX;
		pOffset[VERTEX_INDEX_Y] = this.mCenterY;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
