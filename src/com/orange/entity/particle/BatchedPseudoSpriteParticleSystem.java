package com.orange.entity.particle;

import com.orange.engine.camera.Camera;
import com.orange.entity.Entity;
import com.orange.entity.IEntityFactory;
import com.orange.entity.particle.emitter.IParticleEmitter;
import com.orange.entity.sprite.batch.SpriteBatch;
import com.orange.opengl.texture.region.ITextureRegion;
import com.orange.opengl.util.GLState;
import com.orange.opengl.vbo.VertexBufferObjectManager;
import com.orange.util.color.ColorUtils;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class BatchedPseudoSpriteParticleSystem extends BlendFunctionParticleSystem<Entity> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	protected final ITextureRegion mTextureRegion;
	protected final SpriteBatch mSpriteBatch;

	// ===========================================================
	// Constructors
	// ===========================================================

	public BatchedPseudoSpriteParticleSystem(final IParticleEmitter pParticleEmitter, final float pRateMinimum, final float pRateMaximum, final int pParticlesMaximum, final ITextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager) {
		this(0, 0, pParticleEmitter, pRateMinimum, pRateMaximum, pParticlesMaximum, pTextureRegion, pVertexBufferObjectManager);
	}

	public BatchedPseudoSpriteParticleSystem(final float pX, final float pY, final IParticleEmitter pParticleEmitter, final float pRateMinimum, final float pRateMaximum, final int pParticlesMaximum, final ITextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, new IEntityFactory<Entity>() {
			@Override
			public Entity create(final float pX, final float pY) {
				return new Entity(pX, pY);
			}
		}, pParticleEmitter, pRateMinimum, pRateMaximum, pParticlesMaximum);

		this.mTextureRegion = pTextureRegion;

		this.mSpriteBatch = new SpriteBatch(pTextureRegion.getTexture(), pParticlesMaximum, pVertexBufferObjectManager);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void onManagedDraw(final GLState pGLState, final Camera pCamera) {
		this.mSpriteBatch.setIndex(0);

		final Particle<Entity>[] particles = this.mParticles;
		for(int i = this.mParticlesAlive - 1; i >= 0; i--) {
			final Entity entity = particles[i].getEntity();

			/* In order to support alpha changes of the sprites inside the spritebatch,
			 * we have to 'premultiply' the RGB channels of the sprite with its alpha channel. */
			final float alpha = entity.getAlpha();
			final float colorABGRPackedInt = ColorUtils.convertRGBAToABGRPackedFloat(entity.getRed() * alpha, entity.getGreen() * alpha, entity.getBlue() * alpha, alpha);

			this.mSpriteBatch.drawWithoutChecks(this.mTextureRegion, entity, this.mTextureRegion.getWidth(), this.mTextureRegion.getHeight(), colorABGRPackedInt);
		}
		this.mSpriteBatch.submit();

		this.mSpriteBatch.onDraw(pGLState, pCamera);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
