package com.orange.engine.handler.physics;

import com.orange.engine.handler.BaseEntityUpdateHandler;
import com.orange.entity.IEntity;

/**
 * 物理处理
 * (c) OrangeGame 2012 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class PhysicsHandler extends BaseEntityUpdateHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	/**是否激活*/
	private boolean mEnabled = true;

	protected float mAccelerationX = 0;
	protected float mAccelerationY = 0;

	protected float mVelocityX = 0;
	protected float mVelocityY = 0;
	/**加速度*/
	protected float mAngularVelocity = 0;

	// ===========================================================
	// Constructors
	// ===========================================================

	public PhysicsHandler(final IEntity pEntity) {
		super(pEntity);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public boolean isEnabled() {
		return this.mEnabled;
	}

	public void setEnabled(final boolean pEnabled) {
		this.mEnabled = pEnabled;
	}

	public float getVelocityX() {
		return this.mVelocityX;
	}

	public float getVelocityY() {
		return this.mVelocityY;
	}

	public void setVelocityX(final float pVelocityX) {
		this.mVelocityX = pVelocityX;
	}

	public void setVelocityY(final float pVelocityY) {
		this.mVelocityY = pVelocityY;
	}

	public void setVelocity(final float pVelocity) {
		this.mVelocityX = pVelocity;
		this.mVelocityY = pVelocity;
	}

	public void setVelocity(final float pVelocityX, final float pVelocityY) {
		this.mVelocityX = pVelocityX;
		this.mVelocityY = pVelocityY;
	}

	public float getAccelerationX() {
		return this.mAccelerationX;
	}

	public float getAccelerationY() {
		return this.mAccelerationY;
	}

	public void setAccelerationX(final float pAccelerationX) {
		this.mAccelerationX = pAccelerationX;
	}

	public void setAccelerationY(final float pAccelerationY) {
		this.mAccelerationY = pAccelerationY;
	}

	public void setAcceleration(final float pAccelerationX, final float pAccelerationY) {
		this.mAccelerationX = pAccelerationX;
		this.mAccelerationY = pAccelerationY;
	}

	public void setAcceleration(final float pAcceleration) {
		this.mAccelerationX = pAcceleration;
		this.mAccelerationY = pAcceleration;
	}

	public void accelerate(final float pAccelerationX, final float pAccelerationY) {
		this.mAccelerationX += pAccelerationX;
		this.mAccelerationY += pAccelerationY;
	}
	
	public float getAngularVelocity() {
		return this.mAngularVelocity;
	}

	public void setAngularVelocity(final float pAngularVelocity) {
		this.mAngularVelocity = pAngularVelocity;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void onUpdate(final float pSecondsElapsed, final IEntity pEntity) {
		if(this.mEnabled) {
			/* Apply linear acceleration. */
			final float accelerationX = this.mAccelerationX;
			final float accelerationY = this.mAccelerationY;
			if(accelerationX != 0 || accelerationY != 0) {
				this.mVelocityX += accelerationX * pSecondsElapsed;
				this.mVelocityY += accelerationY * pSecondsElapsed;
			}

			/* Apply angular velocity. */
			final float angularVelocity = this.mAngularVelocity;
			if(angularVelocity != 0) {
				pEntity.setRotation(pEntity.getRotation() + angularVelocity * pSecondsElapsed);
			}

			/* Apply linear velocity. */
			final float velocityX = this.mVelocityX;
			final float velocityY = this.mVelocityY;
			if(velocityX != 0 || velocityY != 0) {
				pEntity.setPosition(pEntity.getX() + velocityX * pSecondsElapsed, pEntity.getY() + velocityY * pSecondsElapsed);
			}
		}
	}

	@Override
	public void reset() {
		this.mAccelerationX = 0;
		this.mAccelerationY = 0;
		this.mVelocityX = 0;
		this.mVelocityY = 0;
		this.mAngularVelocity = 0;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}