package com.orange.entity.particle.initializer;

import com.orange.entity.particle.Particle;
import com.orange.entity.shape.IShape;
import com.orange.entity.shape.Shape;

import android.opengl.GLES20;

/**
 * Sets the blend function used to draw the {@link Particle} during its lifespan. Only applicable to {@link Shape}s.
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 * 
 */
public class BlendFunctionParticleInitializer<T extends IShape> implements IParticleInitializer<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	protected int mBlendFunctionSource;
	protected int mBlendFunctionDestination;

	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * Blend function set for each spawned {@link Particle}. Use {@link GLES20} constants for setting functions.
	 */
	public BlendFunctionParticleInitializer(final int pBlendFunctionSource, final int pBlendFunctionDestination) {
		this.mBlendFunctionSource = pBlendFunctionSource;
		this.mBlendFunctionDestination = pBlendFunctionDestination;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onInitializeParticle(final Particle<T> pParticle) {
		pParticle.getEntity().setBlendFunction(this.mBlendFunctionSource, this.mBlendFunctionDestination);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
