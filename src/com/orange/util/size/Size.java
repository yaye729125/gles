package com.orange.util.size;
/**
 * (c) OrangeGame 2012
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class Size {

	public static final int SIZE_INFINITE = Integer.MAX_VALUE;

	private float mWidth = 0.0f;
	private float mHeight = 0.0f;

	public Size() {
		
	}

	public Size(float pWidth, float pHeight) {
		this.mWidth = pWidth;
		this.mHeight = pHeight;
	}

	public float getWidth() {
		return mWidth;
	}

	public void setWidth(float pWidth) {
		this.mWidth = pWidth;
	}

	public float getHeight() {
		return mHeight;
	}

	public void setHeight(float pHeight) {
		this.mHeight = pHeight;
	}
	
	public void setSize(float pWidth, float pHeight){
		this.mWidth = pWidth;
		this.mHeight = pHeight;
	}

}
