package com.orange.engine.camera;

/**
 * 区域摄像机
 * (c) OrangeGame 2012 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class BoundCamera extends Camera {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	/**是否可以截取*/
	protected boolean mBoundsEnabled;
	/**区域的X轴最小值*/
	protected float mBoundsXMin;
	/**区域的X轴最大值*/
	protected float mBoundsXMax;
	/**区域的Y轴最小值*/
	protected float mBoundsYMin;
	/**区域的Y轴最大值*/
	protected float mBoundsYMax;
	/**区域的X轴中心值*/
	protected float mBoundsCenterX;
	/**区域的Y轴中心值*/
	protected float mBoundsCenterY;
	/**区域的宽度*/
	protected float mBoundsWidth;
	/**区域的高度*/
	protected float mBoundsHeight;

	// ===========================================================
	// Constructors
	// ===========================================================
	/**
	 * 
	 * @param pX x坐标
	 * @param pY y坐标
	 * @param pWidth 宽度
	 * @param pHeight 高度
	 */
	public BoundCamera(final float pX, final float pY, final float pWidth, final float pHeight) {
		super(pX, pY, pWidth, pHeight);
	}
	
	/**
	 * 
	 * @param pX x坐标
	 * @param pY y坐标
	 * @param pWidth  宽度
	 * @param pHeight 高度
	 * @param pBoundMinX x最小值
	 * @param pBoundMaxX x最大值
	 * @param pBoundMinY y最小值
	 * @param pBoundMaxY y最大值
	 */
	public BoundCamera(final float pX, final float pY, final float pWidth, final float pHeight, final float pBoundMinX, final float pBoundMaxX, final float pBoundMinY, final float pBoundMaxY) {
		super(pX, pY, pWidth, pHeight);
		this.setBounds(pBoundMinX, pBoundMinY, pBoundMaxX, pBoundMaxY);
		this.mBoundsEnabled = true;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public boolean isBoundsEnabled() {
		return this.mBoundsEnabled;
	}

	public void setBoundsEnabled(final boolean pBoundsEnabled) {
		this.mBoundsEnabled = pBoundsEnabled;
	}

	/**
	 * 设置边界
	 * @param pBoundsXMin x最小值
	 * @param pBoundsYMin y最大值
	 * @param pBoundsXMax x最大值
	 * @param pBoundsYMax y最大值
	 */
	public void setBounds(final float pBoundsXMin, final float pBoundsYMin, final float pBoundsXMax, final float pBoundsYMax) {
		this.mBoundsXMin = pBoundsXMin;
		this.mBoundsXMax = pBoundsXMax;
		this.mBoundsYMin = pBoundsYMin;
		this.mBoundsYMax = pBoundsYMax;

		this.mBoundsWidth = this.mBoundsXMax - this.mBoundsXMin;
		this.mBoundsHeight = this.mBoundsYMax - this.mBoundsYMin;

		this.mBoundsCenterX = this.mBoundsXMin + this.mBoundsWidth * 0.5f;
		this.mBoundsCenterY = this.mBoundsYMin + this.mBoundsHeight * 0.5f;
	}

	public float getBoundsXMin() {
		return this.mBoundsXMin;
	}

	public float getBoundsXMax() {
		return this.mBoundsXMax;
	}

	public float getBoundsYMin() {
		return this.mBoundsYMin;
	}

	public float getBoundsYMax() {
		return this.mBoundsYMax;
	}

	public float getBoundsWidth() {
		return this.mBoundsWidth;
	}

	public float getBoundsHeight() {
		return this.mBoundsHeight;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	@Override
	public void setCenter(final float pCenterX, final float pCenterY) {
		super.setCenter(pCenterX, pCenterY);
		
		if(this.mBoundsEnabled) {
			this.ensureInBounds();
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================
	/**
	 * 确保在范围内
	 */
	protected void ensureInBounds() {
		final float centerX;
		if(this.mBoundsWidth < this.getWidth()) {
			centerX = this.mBoundsCenterX;
		} else {
			centerX = getBoundedX(this.getCenterX()); 
		}
		final float centerY;
		if(this.mBoundsHeight < this.getHeight()) {
			centerY = this.mBoundsCenterY;
		} else {
			centerY = getBoundedY(this.getCenterY()); 
		}
		super.setCenter(centerX, centerY);
	}
	
	/**
	 * 获取区域X值
	 * @param 
	 * @return
	 */
	protected float getBoundedX(final float pX) {
		final float minXBoundExceededAmount = this.mBoundsXMin - this.getXMin();
		final boolean minXBoundExceeded = minXBoundExceededAmount > 0;

		final float maxXBoundExceededAmount = this.getXMax() - this.mBoundsXMax;
		final boolean maxXBoundExceeded = maxXBoundExceededAmount > 0;

		if(minXBoundExceeded) {
			if(maxXBoundExceeded) {
				/* Min and max X exceeded. */
				return pX - maxXBoundExceededAmount + minXBoundExceededAmount;
			} else {
				/* Only min X exceeded. */
				return pX + minXBoundExceededAmount;
			}
		} else {
			if(maxXBoundExceeded) {
				/* Only max X exceeded. */
				return pX - maxXBoundExceededAmount;
			} else {
				/* Nothing exceeded. */
				return pX;
			}
		}
	}

	/**
	 * 获取区域Y值
	 * @param 
	 * @return
	 */
	protected float getBoundedY(final float pY) {
		final float minYBoundExceededAmount = this.mBoundsYMin - this.getYMin();
		final boolean minYBoundExceeded = minYBoundExceededAmount > 0;

		final float maxYBoundExceededAmount = this.getYMax() - this.mBoundsYMax;
		final boolean maxYBoundExceeded = maxYBoundExceededAmount > 0;

		if(minYBoundExceeded) {
			if(maxYBoundExceeded) {
				/* Min and max Y exceeded. */
				return pY - maxYBoundExceededAmount + minYBoundExceededAmount;
			} else {
				/* Only min Y exceeded. */
				return pY + minYBoundExceededAmount;
			}
		} else {
			if(maxYBoundExceeded) {
				/* Only max Y exceeded. */
				return pY - maxYBoundExceededAmount;
			} else {
				/* Nothing exceeded. */
				return pY;
			}
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
