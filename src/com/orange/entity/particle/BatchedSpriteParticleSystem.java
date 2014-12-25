package com.orange.entity.particle;

import com.orange.engine.camera.Camera;
import com.orange.entity.IEntityFactory;
import com.orange.entity.particle.emitter.IParticleEmitter;
import com.orange.entity.sprite.Sprite;
import com.orange.entity.sprite.UncoloredSprite;
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
public class BatchedSpriteParticleSystem extends BlendFunctionParticleSystem<UncoloredSprite> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	protected final SpriteBatch mSpriteBatch;

	// ===========================================================
	// Constructors
	// ===========================================================

	public BatchedSpriteParticleSystem(final IParticleEmitter pParticleEmitter, final float pRateMinimum, final float pRateMaximum, final int pParticlesMaximum, final ITextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager) {
		this(0, 0, pParticleEmitter, pRateMinimum, pRateMaximum, pParticlesMaximum, pTextureRegion, pVertexBufferObjectManager); 
	}

	public BatchedSpriteParticleSystem(final float pX, final float pY, final IParticleEmitter pParticleEmitter, final float pRateMinimum, final float pRateMaximum, final int pParticlesMaximum, final ITextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, new IEntityFactory<UncoloredSprite>() {
			@Override
			public UncoloredSprite create(final float pX, final float pY) {
				/* We can create an uncolored sprite, since */
				return new UncoloredSprite(pX, pY, pTextureRegion, pVertexBufferObjectManager);
			}
		}, pParticleEmitter, pRateMinimum, pRateMaximum, pParticlesMaximum);

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

		final Particle<UncoloredSprite>[] particles = this.mParticles;
		for(int i = this.mParticlesAlive - 1; i >= 0; i--) {
			final Sprite sprite = particles[i].getEntity();

			/* In order to support alpha changes of the sprites inside the spritebatch,
			 * we have to 'premultiply' the RGB channels of the sprite with its alpha channel. */
			final float alpha = sprite.getAlpha();
			final float colorABGRPackedInt = ColorUtils.convertRGBAToABGRPackedFloat(sprite.getRed() * alpha, sprite.getGreen() * alpha, sprite.getBlue() * alpha, alpha);

			this.mSpriteBatch.drawWithoutChecks(sprite, colorABGRPackedInt);
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
