package com.orange.entity.particle.initializer;

import com.orange.entity.IEntity;
import com.orange.entity.particle.Particle;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class VelocityParticleInitializer<T extends IEntity> extends BaseDoubleValueParticleInitializer<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public VelocityParticleInitializer(final float pVelocity) {
		this(pVelocity, pVelocity, pVelocity, pVelocity);
	}

	public VelocityParticleInitializer(final float pVelocityX, final float pVelocityY) {
		this(pVelocityX, pVelocityX, pVelocityY, pVelocityY);
	}

	/**
	 * 速率粒子初始化器
	 * @param 最小速率X
	 * @param 最大速率X
	 * @param 最小速率Y
	 * @param 最大速率Y*/
	public VelocityParticleInitializer(final float pMinVelocityX, final float pMaxVelocityX, final float pMinVelocityY, final float pMaxVelocityY) {
		super(pMinVelocityX, pMaxVelocityX, pMinVelocityY, pMaxVelocityY);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public float getMinVelocityX() {
		return this.mMinValue;
	}

	public float getMaxVelocityX() {
		return this.mMaxValue;
	}

	public float getMinVelocityY() {
		return this.mMinValueB;
	}

	public float getMaxVelocityY() {
		return this.mMaxValueB;
	}

	public void setVelocityX(final float pVelocityX) {
		this.mMinValue = pVelocityX;
		this.mMaxValue = pVelocityX;
	}

	public void setVelocityY(final float pVelocityY) {
		this.mMinValueB = pVelocityY;
		this.mMaxValueB = pVelocityY;
	}

	public void setVelocityX(final float pMinVelocityX, final float pMaxVelocityX) {
		this.mMinValue = pMinVelocityX;
		this.mMaxValue = pMaxVelocityX;
	}

	public void setVelocityY(final float pMinVelocityY, final float pMaxVelocityY) {
		this.mMinValueB = pMinVelocityY;
		this.mMaxValueB = pMaxVelocityY;
	}

	public void setVelocity(final float pMinVelocityX, final float pMaxVelocityX, final float pMinVelocityY, final float pMaxVelocityY) {
		this.mMinValue = pMinVelocityX;
		this.mMaxValue = pMaxVelocityX;
		this.mMinValueB = pMinVelocityY;
		this.mMaxValueB = pMaxVelocityY;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onInitializeParticle(final Particle<T> pParticle, final float pVelocityX, final float pVelocityY) {
		pParticle.getPhysicsHandler().setVelocity(pVelocityX, pVelocityY);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
