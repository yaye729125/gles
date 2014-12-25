package com.orange.entity.particle;

import com.orange.engine.camera.Camera;
import com.orange.entity.IEntity;
import com.orange.entity.IEntityFactory;
import com.orange.entity.particle.emitter.IParticleEmitter;
import com.orange.entity.shape.IShape;
import com.orange.opengl.util.GLState;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class BlendFunctionParticleSystem<T extends IEntity> extends ParticleSystem<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	protected boolean mBlendingEnabled = true;
	protected int mBlendFunctionSource = IShape.BLENDFUNCTION_SOURCE_DEFAULT;
	protected int mBlendFunctionDestination = IShape.BLENDFUNCTION_DESTINATION_DEFAULT;

	// ===========================================================
	// Constructors
	// ===========================================================

	public BlendFunctionParticleSystem(IEntityFactory<T> pEntityFactory, IParticleEmitter pParticleEmitter, float pRateMinimum, float pRateMaximum, int pParticlesMaximum) {
		super(pEntityFactory, pParticleEmitter, pRateMinimum, pRateMaximum, pParticlesMaximum);
	}

	public BlendFunctionParticleSystem(float pX, float pY, IEntityFactory<T> pEntityFactory, IParticleEmitter pParticleEmitter, float pRateMinimum, float pRateMaximum, int pParticlesMaximum) {
		super(pX, pY, pEntityFactory, pParticleEmitter, pRateMinimum, pRateMaximum, pParticlesMaximum);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public boolean isBlendingEnabled() {
		return this.mBlendingEnabled;
	}

	public void setBlendingEnabled(final boolean pBlendingEnabled) {
		this.mBlendingEnabled = pBlendingEnabled;
	}

	public int getBlendFunctionSource() {
		return this.mBlendFunctionSource;
	}

	public void setBlendFunctionSource(final int pBlendFunctionSource) {
		this.mBlendFunctionSource = pBlendFunctionSource;
	}

	public int getBlendFunctionDestination() {
		return this.mBlendFunctionDestination;
	}

	public void setBlendFunctionDestination(final int pBlendFunctionDestination) {
		this.mBlendFunctionDestination = pBlendFunctionDestination;
	}

	public void setBlendFunction(final int pBlendFunctionSource, final int pBlendFunctionDestination) {
		this.mBlendFunctionSource = pBlendFunctionSource;
		this.mBlendFunctionDestination = pBlendFunctionDestination;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void preDraw(final GLState pGLState, final Camera pCamera) {
		if(this.mBlendingEnabled) {
			pGLState.enableBlend();
			pGLState.blendFunction(this.mBlendFunctionSource, this.mBlendFunctionDestination);
		}
	}

	@Override
	protected void postDraw(final GLState pGLState, final Camera pCamera) {
		if(this.mBlendingEnabled) {
			pGLState.disableBlend();
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
