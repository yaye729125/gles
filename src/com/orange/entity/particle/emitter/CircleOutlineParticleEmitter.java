package com.orange.entity.particle.emitter;

import static com.orange.util.Constants.VERTEX_INDEX_X;
import static com.orange.util.Constants.VERTEX_INDEX_Y;

import com.orange.util.math.MathConstants;
import com.orange.util.math.MathUtils;

import android.util.FloatMath;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class CircleOutlineParticleEmitter extends BaseCircleParticleEmitter {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public CircleOutlineParticleEmitter(final float pCenterX, final float pCenterY, final float pRadius) {
		super(pCenterX, pCenterY, pRadius);
	}

	public CircleOutlineParticleEmitter(final float pCenterX, final float pCenterY, final float pRadiusX, final float pRadiusY) {
		super(pCenterX, pCenterY, pRadiusX, pRadiusY);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void getPositionOffset(final float[] pOffset) {
		final float random = MathUtils.RANDOM.nextFloat() * MathConstants.PI * 2;
		pOffset[VERTEX_INDEX_X] = this.mCenterX + FloatMath.cos(random) * this.mRadiusX;
		pOffset[VERTEX_INDEX_Y] = this.mCenterY + FloatMath.sin(random) * this.mRadiusY;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
