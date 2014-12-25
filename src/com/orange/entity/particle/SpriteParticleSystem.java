package com.orange.entity.particle;

import com.orange.entity.IEntityFactory;
import com.orange.entity.particle.emitter.IParticleEmitter;
import com.orange.entity.sprite.Sprite;
import com.orange.opengl.texture.region.ITextureRegion;
import com.orange.opengl.vbo.VertexBufferObjectManager;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class SpriteParticleSystem extends ParticleSystem<Sprite> {
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
*	精灵粒子系统
*	@param pParticleEmitter 粒子发射器
*	@param	pRateMinimum：一秒中能同时发射到屏幕上的粒子最小的数量
*	@param pRateMaximum：一秒中能同时发射到屏幕上的粒子最大的数量
*	@param pParticlesMaximum：屏幕上允许可以存活的最大的粒子数
*	@param pTextureRegion：选择表达的纹理*/
	public SpriteParticleSystem(final IParticleEmitter pParticleEmitter, final float pRateMinimum, final float pRateMaximum, final int pParticlesMaximum, final ITextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager) {
		this(0, 0, pParticleEmitter, pRateMinimum, pRateMaximum, pParticlesMaximum, pTextureRegion, pVertexBufferObjectManager);
	}

	public SpriteParticleSystem(final float pX, final float pY, final IParticleEmitter pParticleEmitter, final float pRateMinimum, final float pRateMaximum, final int pParticlesMaximum, final ITextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, new IEntityFactory<Sprite>() {
			@Override
			public Sprite create(final float pX, final float pY) {
				return new Sprite(pX, pY, pTextureRegion, pVertexBufferObjectManager);
			}
		}, pParticleEmitter, pRateMinimum, pRateMaximum, pParticlesMaximum);
	}

	protected SpriteParticleSystem(final float pX, final float pY, final IEntityFactory<Sprite> pEntityFactory, final IParticleEmitter pParticleEmitter, final float pRateMinimum, final float pRateMaximum, final int pParticlesMaximum) {
		super(pX, pY, pEntityFactory, pParticleEmitter, pRateMinimum, pRateMaximum, pParticlesMaximum);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

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
