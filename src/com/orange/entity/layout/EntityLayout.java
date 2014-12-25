package com.orange.entity.layout;

import com.orange.entity.Entity;
import com.orange.entity.IEntity;
import com.orange.entity.scene.Scene;
import com.orange.util.size.Size;

public class EntityLayout extends BaseEntityLayout {

	public EntityLayout(LayoutParams pLayoutParams, Scene pScene) {
		this(0, 0, pLayoutParams, pScene);
	}

	public EntityLayout(float pX, float pY, LayoutParams pLayoutParams, Scene pScene) {
		super(pX, pY, Size.SIZE_INFINITE, Size.SIZE_INFINITE, pScene);
		// TODO Auto-generated constructor stub
		this.setLayoutParams(pLayoutParams);
	}

	// ===========================================================
	// 布局处理
	// ===========================================================

	@Override
	public void onMeasure(float pWidthMeasureSpec, float pHeightMeasureSpec) {
		// TODO Auto-generated method stub
		EntityLayout.LayoutParams layoutParams = this.getLayoutParams();
		//处理宽
		if (layoutParams == null || layoutParams.mWidth == EntityLayout.LayoutParams.WRAP_CONTENT) {
//			pWidthMeasureSpec = Float.MAX_VALUE;
		}else if (layoutParams.mWidth == EntityLayout.LayoutParams.MATCH_PARENT) {
			pWidthMeasureSpec = this.getScene().getCameraWidth();
		}else {
			pWidthMeasureSpec = layoutParams.mWidth;
		}
		//处理高
		if (layoutParams == null || layoutParams.mHeight == EntityLayout.LayoutParams.WRAP_CONTENT) {
//			pHeightMeasureSpec = Float.MAX_VALUE;
		}else if (layoutParams.mHeight == EntityLayout.LayoutParams.MATCH_PARENT) {
			pHeightMeasureSpec = this.getScene().getCameraHeight();
		}else {
			pHeightMeasureSpec = layoutParams.mHeight;
		}
		//
		super.onMeasure(pWidthMeasureSpec, pHeightMeasureSpec);
	}
	
	public void setSize(){
		EntityLayout.LayoutParams layoutParams = this.getLayoutParams();
		float minX = 0.0f;
		float minY = 0.0f;
		float maxX = 0.0f;
		float maxY = 0.0f;
		if (layoutParams.mWidth == EntityLayout.LayoutParams.WRAP_CONTENT ||layoutParams.mHeight == EntityLayout.LayoutParams.WRAP_CONTENT) {
			int childCount = this.getChildCount();
			for (int i = childCount - 1; i >= 0; i--) {
				Entity child = (Entity) this.getChildByIndex(i);
				if (child != null) {
					if (child.getX() < minX) {
						minX = child.getX();
					}
					if (child.getRightX() > maxX) {
						maxX = child.getRightX();
					}
					if (child.getY() < minY) {
						minY = child.getY();
					}
					if (child.getBottomY() > maxY) {
						maxY = child.getBottomY();
					}
				}
			}
		}
		if (layoutParams.mWidth == EntityLayout.LayoutParams.MATCH_PARENT) {
			this.setWidth(this.getScene().getCameraWidth());
		}else if (layoutParams.mWidth == EntityLayout.LayoutParams.WRAP_CONTENT) {
			this.setWidth(maxX - minX);
		}else {
			this.setWidth(layoutParams.mWidth);
		}
		if (layoutParams.mHeight == EntityLayout.LayoutParams.MATCH_PARENT) {
			this.setHeight(this.getScene().getCameraHeight());
		}else if (layoutParams.mHeight == EntityLayout.LayoutParams.WRAP_CONTENT) {
			this.setHeight(maxY - minY);
		}else {
			this.setHeight(layoutParams.mHeight);
		}
	}
	
	// ===========================================================
	// Getter And Setter
	// ===========================================================


	// ===========================================================
	// 布局参数
	// ===========================================================

	public static class LayoutParams {

		public static final float MATCH_PARENT = -1.0f;
		public static final float WRAP_CONTENT = -2.0f;

		public float mWidth;
		public float mHeight;

		public LayoutParams() {
			this.mWidth = WRAP_CONTENT;
			this.mHeight = WRAP_CONTENT;
		}
		
		public LayoutParams(float pWidth, float pHeight){
			this.mWidth = pWidth;
			this.mHeight = pHeight;
		}

	}

	public static class MarginLayoutParams extends EntityLayout.LayoutParams {

		public float mLeftMargin;
		public float mTopMargin;
		public float mRightMargin;
		public float mBottomMargin;

		public MarginLayoutParams() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		public MarginLayoutParams(float pWidth, float pHeight){
			super(pWidth, pHeight);
		}

		public MarginLayoutParams(float left, float top, float right, float bottom) {
			super();
			this.mLeftMargin = left;
			this.mTopMargin = top;
			this.mRightMargin = right;
			this.mBottomMargin = bottom;
		}

		public void setMargins(float left, float top, float right, float bottom) {
			this.mLeftMargin = left;
			this.mTopMargin = top;
			this.mRightMargin = right;
			this.mBottomMargin = bottom;
		}
	}

}
