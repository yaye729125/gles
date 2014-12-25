package com.orange.entity.layout;

import com.orange.entity.Entity;
import com.orange.entity.IEntity;
import com.orange.entity.group.Orientation;
import com.orange.entity.scene.Scene;

public class LinearEntityLayout extends EntityLayout {

	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 1;

	private int mGravity = Gravity.LEFT | Gravity.TOP;
	private Orientation mOrientation = Orientation.VERTICAL;

	public LinearEntityLayout(float pX, float pY, LayoutParams pLayoutParams, Scene pScene) {
		super(pX, pY, pLayoutParams, pScene);
		// TODO Auto-generated constructor stub
	}

	public LinearEntityLayout(LayoutParams pLayoutParams, Scene pScene) {
		super(pLayoutParams, pScene);
		// TODO Auto-generated constructor stub
	}

	public LinearEntityLayout(Scene pScene) {
		super(new LayoutParams(), pScene);
		// TODO Auto-generated constructor stub
	}

	// ===========================================================
	// 布局处理
	// ===========================================================

	@Override
	public void onMeasure(float pWidthMeasureSpec, float pHeightMeasureSpec) {
		// TODO Auto-generated method stub
		EntityLayout.LayoutParams layoutParams = this.getLayoutParams();
		if (layoutParams == null || layoutParams.mWidth == LayoutParams.WRAP_CONTENT || layoutParams.mHeight == LayoutParams.WRAP_CONTENT) {
			int childCount = this.getChildCount();
			//计算宽高和
			float childWidthCount = 0.0f;
			float childHeightCount = 0.0f;
			for (int i = 0; i < childCount; i++) {
				IEntity child =  this.getChildByIndex(i);
				if (child != null) {
					float leftMargin = 0.0f;
					float rightMargin = 0.0f;
					float topMargin = 0.0f;
					float bottomMargin = 0.0f;
					EntityLayout.LayoutParams childLayoutParams = child.getLayoutParams();
					if (childLayoutParams != null && childLayoutParams instanceof EntityLayout.MarginLayoutParams) {
						leftMargin = ((EntityLayout.MarginLayoutParams) childLayoutParams).mLeftMargin;
						rightMargin = ((EntityLayout.MarginLayoutParams) childLayoutParams).mRightMargin;
						topMargin = ((EntityLayout.MarginLayoutParams) childLayoutParams).mTopMargin;
						bottomMargin = ((EntityLayout.MarginLayoutParams) childLayoutParams).mBottomMargin;
					}
					if (this.mOrientation == Orientation.HORIZONTAL) {
						float childWidth = child.getWidth() + leftMargin + rightMargin;
						childWidthCount += childWidth;
						if (child.getHeight() > childHeightCount) {
							childHeightCount = child.getHeight();
						}
					} else {
						float childHeight = child.getHeight() + topMargin + bottomMargin;
						childHeightCount += childHeight;
						if (child.getWidth() > childWidthCount) {
							childWidthCount = child.getWidth();
						}
					}
				}
			}
			if (layoutParams.mWidth == LayoutParams.WRAP_CONTENT) {
				pWidthMeasureSpec = childWidthCount;
			}
			if (layoutParams.mHeight == LayoutParams.WRAP_CONTENT) {
				pHeightMeasureSpec = childHeightCount;
			}
		}
		super.onMeasure(pWidthMeasureSpec, pHeightMeasureSpec);
	}

	@Override
	public void onLayout() {
		// TODO Auto-generated method stub
		super.onLayout();
		this.layoutLinear();
		this.setSize();
//		this.layoutLinear();
	}

	protected void layoutLinear() {
		final int majorGravity = this.mGravity & Gravity.VERTICAL_GRAVITY_MASK;
		final int minorGravity = this.mGravity & Gravity.HORIZONTAL_GRAVITY_MASK;

		if (majorGravity != Gravity.TOP) {
			switch (majorGravity) {
			case Gravity.BOTTOM:
				this.layoutBottom();
				break;

			case Gravity.CENTER_VERTICAL:
				this.layoutCenterVertical();
				break;
			}
		} else {
			this.layoutTop();
		}

		if (minorGravity != Gravity.LEFT) {
			switch (minorGravity) {
			case Gravity.CENTER_HORIZONTAL:
				this.layoutCenterHorizontal();
				break;

			case Gravity.RIGHT:
				this.layoutRight();
				break;
			}
		} else {
			this.layoutLeft();
		}
	}

	private void layoutLeft() {
		int childCount = this.getChildCount();
		float lastChildRightX = 0.0f;
		for (int i = 0; i < childCount; i++) {
			IEntity child =  this.getChildByIndex(i);
			if (child != null) {
				float leftMargin = 0.0f;
				float rightMargin = 0.0f;
				EntityLayout.LayoutParams childLayoutParams = child.getLayoutParams();
				if (childLayoutParams != null && childLayoutParams instanceof EntityLayout.MarginLayoutParams) {
					leftMargin = ((EntityLayout.MarginLayoutParams) childLayoutParams).mLeftMargin;
					rightMargin = ((EntityLayout.MarginLayoutParams) childLayoutParams).mRightMargin;
				}
				if (this.mOrientation == Orientation.HORIZONTAL) {
					float layoutX = lastChildRightX + leftMargin;
					child.layoutX(layoutX);
					lastChildRightX = child.getRightX() + rightMargin;
				} else {
					float layoutX = 0.0f + leftMargin;
					child.layoutX(layoutX);
				}
			}
		}
	}

	private void layoutRight() {
		int childCount = this.getChildCount();
		float lastChildLeftX = this.getMeasureWidth();
		for (int i = childCount - 1; i >= 0; i--) {
			IEntity child =  this.getChildByIndex(i);
			if (child != null) {
				float leftMargin = 0.0f;
				float rightMargin = 0.0f;
				EntityLayout.LayoutParams childLayoutParams = child.getLayoutParams();
				if (childLayoutParams != null && childLayoutParams instanceof EntityLayout.MarginLayoutParams) {
					leftMargin = ((EntityLayout.MarginLayoutParams) childLayoutParams).mLeftMargin;
					rightMargin = ((EntityLayout.MarginLayoutParams) childLayoutParams).mRightMargin;
				}
				if (this.mOrientation == Orientation.HORIZONTAL) {
					float layoutX = lastChildLeftX - rightMargin - child.getWidth();
					child.layoutX(layoutX);
					lastChildLeftX = child.getX() - leftMargin;
				} else {
					float layoutX = this.getMeasureWidth() - rightMargin - child.getWidth();
					child.layoutX(layoutX);
				}
			}
		}
	}
	
	private void layoutCenterHorizontal(){
		int childCount = this.getChildCount();
		//计算宽高和
		float childWidthCount = 0.0f;
		for (int i = 0; i < childCount; i++) {
			IEntity child =  this.getChildByIndex(i);
			if (child != null) {
				float leftMargin = 0.0f;
				float rightMargin = 0.0f;
				EntityLayout.LayoutParams childLayoutParams = child.getLayoutParams();
				if (childLayoutParams != null && childLayoutParams instanceof EntityLayout.MarginLayoutParams) {
					leftMargin = ((EntityLayout.MarginLayoutParams) childLayoutParams).mLeftMargin;
					rightMargin = ((EntityLayout.MarginLayoutParams) childLayoutParams).mRightMargin;
				}
				if (this.mOrientation == Orientation.HORIZONTAL) {
					float childWidth = child.getWidth() + leftMargin + rightMargin;
					childWidthCount += childWidth;
				} else {
					if (child.getWidth() > childWidthCount) {
						childWidthCount = child.getWidth();
					}
				}
			}
		}
		//开始布局
		float lastChildRightX = this.getMeasureWidth() / 2.0f - childWidthCount / 2.0f;
		if (lastChildRightX < 0.0f) {
			lastChildRightX = 0.0f;
		}
		for (int i = 0; i < childCount; i++) {
			IEntity child =  this.getChildByIndex(i);
			if (child != null) {
				float leftMargin = 0.0f;
				float rightMargin = 0.0f;
				EntityLayout.LayoutParams childLayoutParams = child.getLayoutParams();
				if (childLayoutParams != null && childLayoutParams instanceof EntityLayout.MarginLayoutParams) {
					leftMargin = ((EntityLayout.MarginLayoutParams) childLayoutParams).mLeftMargin;
					rightMargin = ((EntityLayout.MarginLayoutParams) childLayoutParams).mRightMargin;
				}
				if (this.mOrientation == Orientation.HORIZONTAL) {
					float layoutX = lastChildRightX + leftMargin;
					child.layoutX(layoutX);
					lastChildRightX = child.getRightX() + rightMargin;
				} else {
					float layoutX = this.getMeasureWidth() / 2.0f - child.getWidth() /2.0f;
					if (layoutX < 0.0f) {
						layoutX = 0.0f;
					}
					child.layoutX(layoutX);
				}
			}
		}
	}

	private void layoutTop() {
		int childCount = this.getChildCount();
		float lastChildBottomY = 0.0f;
		for (int i = 0; i < childCount; i++) {
			IEntity child =  this.getChildByIndex(i);
			if (child != null) {
				float topMargin = 0.0f;
				float bottomMargin = 0.0f;
				EntityLayout.LayoutParams childLayoutParams = child.getLayoutParams();
				if (childLayoutParams != null && childLayoutParams instanceof EntityLayout.MarginLayoutParams) {
					topMargin = ((EntityLayout.MarginLayoutParams) childLayoutParams).mTopMargin;
					bottomMargin = ((EntityLayout.MarginLayoutParams) childLayoutParams).mBottomMargin;
				}
				if (this.mOrientation == Orientation.VERTICAL) {
					float layoutY = lastChildBottomY + topMargin;
					child.layoutY(layoutY);
					lastChildBottomY = child.getBottomY() + bottomMargin;
				} else {
					float layoutY = 0.0f + topMargin;
					child.layoutY(layoutY);
				}
			}
		}
	}

	private void layoutBottom() {
		int childCount = this.getChildCount();
		float lastChildTopY = this.getMeasureHeight();
		for (int i = childCount - 1; i >= 0; i--) {
			IEntity child =  this.getChildByIndex(i);
			if (child != null) {
				float topMargin = 0.0f;
				float bottomMargin = 0.0f;
				EntityLayout.LayoutParams childLayoutParams = child.getLayoutParams();
				if (childLayoutParams != null && childLayoutParams instanceof EntityLayout.MarginLayoutParams) {
					topMargin = ((EntityLayout.MarginLayoutParams) childLayoutParams).mTopMargin;
					bottomMargin = ((EntityLayout.MarginLayoutParams) childLayoutParams).mBottomMargin;
				}
				if (this.mOrientation == Orientation.VERTICAL) {
					float layoutY = lastChildTopY - bottomMargin - child.getHeight();
					child.layoutY(layoutY);
					lastChildTopY = child.getY() - topMargin;
				} else {
					float layoutY = this.getMeasureHeight() - bottomMargin - child.getHeight();
					child.layoutY(layoutY);
				}
			}
		}
	}
	
	
	private void layoutCenterVertical(){
		int childCount = this.getChildCount();
		//计算宽高和
		float childHeightCount = 0.0f;
		for (int i = 0; i < childCount; i++) {
			IEntity child =  this.getChildByIndex(i);
			if (child != null) {
				float topMargin = 0.0f;
				float bottomMargin = 0.0f;
				EntityLayout.LayoutParams childLayoutParams = child.getLayoutParams();
				if (childLayoutParams != null && childLayoutParams instanceof EntityLayout.MarginLayoutParams) {
					topMargin = ((EntityLayout.MarginLayoutParams) childLayoutParams).mTopMargin;
					bottomMargin = ((EntityLayout.MarginLayoutParams) childLayoutParams).mBottomMargin;
				}
				if (this.mOrientation == Orientation.HORIZONTAL) {
					if (child.getHeight() > childHeightCount) {
						childHeightCount = child.getHeight();
					}
				} else {
					float childHeight = child.getHeight() + topMargin + bottomMargin;
					childHeightCount += childHeight;
				}
			}
		}
		//开始布局
		float lastChildBottomY = this.getMeasureHeight() / 2.0f - childHeightCount / 2.0f;
		if (lastChildBottomY < 0.0f) {
			lastChildBottomY = 0.0f;
		}
		for (int i = 0; i < childCount; i++) {
			IEntity child =  this.getChildByIndex(i);
			if (child != null) {
				float topMargin = 0.0f;
				float bottomMargin = 0.0f;
				EntityLayout.LayoutParams childLayoutParams = child.getLayoutParams();
				if (childLayoutParams != null && childLayoutParams instanceof EntityLayout.MarginLayoutParams) {
					topMargin = ((EntityLayout.MarginLayoutParams) childLayoutParams).mTopMargin;
					bottomMargin = ((EntityLayout.MarginLayoutParams) childLayoutParams).mBottomMargin;
				}
				if (this.mOrientation == Orientation.HORIZONTAL) {
					float layoutY = this.getMeasureHeight() / 2.0f - child.getHeight() /2.0f;
					if (layoutY < 0.0f) {
						layoutY = 0.0f;
					}
					child.layoutY(layoutY);
				} else {
					float layoutY = lastChildBottomY + topMargin;
					child.layoutY(layoutY);
					lastChildBottomY = child.getBottomY() + bottomMargin;
				}
			}
		}
	}

	// ===========================================================
	// Getter And Setter
	// ===========================================================

	public int getGravity() {
		return mGravity;
	}

	public void setGravity(int pGravity) {
		if (this.mGravity != pGravity) {
			if ((pGravity & Gravity.HORIZONTAL_GRAVITY_MASK) == 0) {
				pGravity |= Gravity.LEFT;
			}
			if ((pGravity & Gravity.VERTICAL_GRAVITY_MASK) == 0) {
				pGravity |= Gravity.TOP;
			}
			this.mGravity = pGravity;
		}
	}

	public Orientation getOrientation() {
		return mOrientation;
	}

	public void setOrientation(Orientation pOrientation) {
		this.mOrientation = pOrientation;
	}

	// ===========================================================
	// 布局 参数
	// ===========================================================

	public static class LayoutParams extends EntityLayout.MarginLayoutParams {

		public LayoutParams() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		public LayoutParams(float pWidth, float pHeight){
			super(pWidth, pHeight);
		}

		public LayoutParams(float left, float top, float right, float bottom) {
			super(left, top, right, bottom);
			// TODO Auto-generated constructor stub
		}

	}

}
