package com.orange.engine.camera;


/**
 * 平滑相机
 * (c) OrangeGame 2012 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class SmoothCamera extends ZoomCamera {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	/**横向速率最大值*/
	protected float mMaxVelocityX;
	/**竖向速率最大值*/
	protected float mMaxVelocityY;
	/**缩放比例最大值*/
	protected float mMaxZoomFactorChange;
	/**中心X值*/
	protected float mTargetCenterX;
	/**中心Y值*/
	protected float mTargetCenterY;
	/**缩放比例*/
	protected float mTargetZoomFactor;

	// ===========================================================
	// Constructors
	// ===========================================================

	public SmoothCamera(final float pX, final float pY, final float pWidth, final float pHeight, final float pMaxVelocityX, final float pMaxVelocityY, final float pMaxZoomFactorChange) {
		super(pX, pY, pWidth, pHeight);
		this.mMaxVelocityX = pMaxVelocityX;
		this.mMaxVelocityY = pMaxVelocityY;
		this.mMaxZoomFactorChange = pMaxZoomFactorChange;

		this.mTargetCenterX = this.getCenterX();
		this.mTargetCenterY = this.getCenterY();

		this.mTargetZoomFactor = 1.0f;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public float getTargetCenterX() {
		return this.mTargetCenterX;
	}

	public float getTargetCenterY() {
		return this.mTargetCenterY;
	}

	public float getTargetZoomFactor() {
		return this.mTargetZoomFactor;
	}

	@Override
	public void setCenter(final float pCenterX, final float pCenterY) {
		this.mTargetCenterX = pCenterX;
		this.mTargetCenterY = pCenterY;
	}

	public void setCenterDirect(final float pCenterX, final float pCenterY) {
		super.setCenter(pCenterX, pCenterY);
		this.mTargetCenterX = pCenterX;
		this.mTargetCenterY = pCenterY;
	}

	@Override
	public void setZoomFactor(final float pZoomFactor) {
		if(this.mTargetZoomFactor != pZoomFactor) {
			if(this.mTargetZoomFactor == this.mZoomFactor) {
				this.mTargetZoomFactor = pZoomFactor;

				this.onSmoothZoomStarted();
			} else {
				this.mTargetZoomFactor = pZoomFactor;
			}
		}
	}
	
	/**
	 * 设置直观缩放比例
	 * @param pZoomFactor
	 */
	public void setZoomFactorDirect(final float pZoomFactor) {
		if(this.mTargetZoomFactor != this.mZoomFactor) {
			this.mTargetZoomFactor = pZoomFactor;
			super.setZoomFactor(pZoomFactor);

			this.onSmoothZoomFinished();
		} else {
			this.mTargetZoomFactor = pZoomFactor;
			super.setZoomFactor(pZoomFactor);
		}
	}

	public float getMaxVelocityX() {
		return this.mMaxVelocityX;
	}

	public void setMaxVelocityX(final float pMaxVelocityX) {
		this.mMaxVelocityX = pMaxVelocityX;
	}

	public float getMaxVelocityY() {
		return this.mMaxVelocityY;
	}

	public void setMaxVelocityY(final float pMaxVelocityY) {
		this.mMaxVelocityY = pMaxVelocityY;
	}

	public void setMaxVelocity(final float pMaxVelocityX, final float pMaxVelocityY) {
		this.mMaxVelocityX = pMaxVelocityX;
		this.mMaxVelocityY = pMaxVelocityY;
	}

	public float getMaxZoomFactorChange() {
		return this.mMaxZoomFactorChange;
	}

	public void setMaxZoomFactorChange(final float pMaxZoomFactorChange) {
		this.mMaxZoomFactorChange = pMaxZoomFactorChange;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	protected void onSmoothZoomStarted() {

	}

	protected void onSmoothZoomFinished() {

	}

	@Override
	public void onUpdate(final float pSecondsElapsed) {
		super.onUpdate(pSecondsElapsed);
		/* Update center. */
		final float currentCenterX = this.getCenterX();
		final float currentCenterY = this.getCenterY();

		final float targetCenterX = this.mTargetCenterX;
		final float targetCenterY = this.mTargetCenterY;

		if(currentCenterX != targetCenterX || currentCenterY != targetCenterY) {
			final float diffX = targetCenterX - currentCenterX;
			final float dX = this.limitToMaxVelocityX(diffX, pSecondsElapsed);

			final float diffY = targetCenterY - currentCenterY;
			final float dY = this.limitToMaxVelocityY(diffY, pSecondsElapsed);

			super.setCenter(currentCenterX + dX, currentCenterY + dY);
		}

		/* Update zoom. */
		final float currentZoom = this.getZoomFactor();

		final float targetZoomFactor = this.mTargetZoomFactor;

		if(currentZoom != targetZoomFactor) {
			final float absoluteZoomDifference = targetZoomFactor - currentZoom;
			final float zoomChange = this.limitToMaxZoomFactorChange(absoluteZoomDifference, pSecondsElapsed);
			super.setZoomFactor(currentZoom + zoomChange);

			if(this.mZoomFactor == this.mTargetZoomFactor) {
				this.onSmoothZoomFinished();
			}
		}
	}
	
	/**
	 * 最大横向速率限制
	 * @param pValue
	 * @param pSecondsElapsed
	 * @return
	 */
	private float limitToMaxVelocityX(final float pValue, final float pSecondsElapsed) {
		if(pValue > 0) {
			return Math.min(pValue, this.mMaxVelocityX * pSecondsElapsed);
		} else {
			return Math.max(pValue, -this.mMaxVelocityX * pSecondsElapsed);
		}
	}

	/**
	 * 最大竖向速率限制
	 * @param pValue
	 * @param pSecondsElapsed
	 * @return
	 */
	private float limitToMaxVelocityY(final float pValue, final float pSecondsElapsed) {
		if(pValue > 0) {
			return Math.min(pValue, this.mMaxVelocityY * pSecondsElapsed);
		} else {
			return Math.max(pValue, -this.mMaxVelocityY * pSecondsElapsed);
		}
	}

	/**
	 * 最大缩放比例限制
	 * @param pValue
	 * @param pSecondsElapsed
	 * @return
	 */
	private float limitToMaxZoomFactorChange(final float pValue, final float pSecondsElapsed) {
		if(pValue > 0) {
			return Math.min(pValue, this.mMaxZoomFactorChange * pSecondsElapsed);
		} else {
			return Math.max(pValue, -this.mMaxZoomFactorChange * pSecondsElapsed);
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
