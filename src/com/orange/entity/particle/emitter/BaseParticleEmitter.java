package com.orange.entity.particle.emitter;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public abstract class BaseParticleEmitter implements IParticleEmitter {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	protected float mCenterX;
	protected float mCenterY;

	// ===========================================================
	// Constructors
	// ===========================================================

	public BaseParticleEmitter(final float pCenterX, final float pCenterY) {
		this.mCenterX = pCenterX;
		this.mCenterY = pCenterY;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public float getCenterX() {
		return this.mCenterX;
	}

	public float getCenterY() {
		return this.mCenterY;
	}

	public void setCenterX(final float pCenterX) {
		this.mCenterX = pCenterX;
	}

	public void setCenterY(final float pCenterY) {
		this.mCenterY = pCenterY;
	}

	public void setCenter(final float pCenterX, final float pCenterY) {
		this.mCenterX = pCenterX;
		this.mCenterY = pCenterY;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onUpdate(final float pSecondsElapsed) {

	}

	@Override
	public void reset() {

	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
