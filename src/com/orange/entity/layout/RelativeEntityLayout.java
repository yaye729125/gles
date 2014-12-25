package com.orange.entity.layout;

import com.orange.entity.Entity;
import com.orange.entity.IEntity;
import com.orange.entity.scene.Scene;

public class RelativeEntityLayout extends EntityLayout {

	public static final int TRUE = -1;
	public static final int FALSE = 0;
	public static final int ALIGN_PARENT_LEFT = 9;
	public static final int ALIGN_PARENT_TOP = 10;
	public static final int ALIGN_PARENT_RIGHT = 11;
	public static final int ALIGN_PARENT_BOTTOM = 12;
	public static final int CENTER_IN_PARENT = 13;
	public static final int CENTER_HORIZONTAL = 14;
	public static final int CENTER_VERTICAL = 15;

	private static final int VERB_COUNT = 16;

	public RelativeEntityLayout(float pX, float pY, LayoutParams pLayoutParams, Scene pScene) {
		super(pX, pY, pLayoutParams, pScene);
		// TODO Auto-generated constructor stub
	}

	public RelativeEntityLayout(LayoutParams pLayoutParams, Scene pScene) {
		super(pLayoutParams, pScene);
		// TODO Auto-generated constructor stub
	}
	
	public RelativeEntityLayout(Scene pScene) {
		this(new LayoutParams(), pScene);
	}

	@Override
	public void onMeasure(float pWidthMeasureSpec, float pHeightMeasureSpec) {
		// TODO Auto-generated method stub
		EntityLayout.LayoutParams layoutParams = this.getLayoutParams();
		if (layoutParams == null || layoutParams.mWidth == LayoutParams.WRAP_CONTENT || layoutParams.mHeight == LayoutParams.WRAP_CONTENT) {
			int childCount = this.getChildCount();
			// 计算宽高和
			float childWidthCount = 0.0f;
			float childHeightCount = 0.0f;
			for (int i = 0; i < childCount; i++) {
				Entity child = (Entity) this.getChildByIndex(i);
				if (child != null) {
					if (child.getHeight() > childHeightCount) {
						childHeightCount = child.getHeight();
					}
					if (child.getWidth() > childWidthCount) {
						childWidthCount = child.getWidth();
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
		this.layoutRelative();
		this.setSize();
	}
	
	
	protected void layoutRelative(){
		int childCount = this.getChildCount();
		for (int i = 0; i < childCount; i++) {
			IEntity child = this.getChildByIndex(i);
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
				if (childLayoutParams != null && childLayoutParams instanceof RelativeEntityLayout.LayoutParams) {
					int[] rules = ((RelativeEntityLayout.LayoutParams)childLayoutParams).getRules();
					if (rules[ALIGN_PARENT_LEFT] == TRUE) {
						this.layoutAlignParentLeft(child, leftMargin);
					}
					if (rules[ALIGN_PARENT_TOP] == TRUE) {
						this.layoutAlignParentTop(child, topMargin);
					}
					if (rules[ALIGN_PARENT_RIGHT] == TRUE) {
						this.layoutAlignParentRight(child, rightMargin);
					}
					if (rules[ALIGN_PARENT_BOTTOM] == TRUE) {
						this.layoutAlignParentBottom(child, bottomMargin);
					}
					if (rules[CENTER_HORIZONTAL] == TRUE) {
						this.layoutCenterHorizontal(child);
					}
					if (rules[CENTER_VERTICAL] == TRUE) {
						this.layoutCenterVertical(child);
					}
					if (rules[CENTER_IN_PARENT] == TRUE) {
						this.layoutCenterHorizontal(child);
						this.layoutCenterVertical(child);
					}
				}else {
					this.layoutAlignParentLeft(child, leftMargin);
					this.layoutAlignParentTop(child, topMargin);
				}
			}
		}
	}
	
	
	private void layoutAlignParentLeft(IEntity pChild, float pLeftMargin){
		pChild.layoutX(0 + pLeftMargin);
	}
	
	private void layoutAlignParentTop(IEntity pChild, float pTopMargin){
		pChild.layoutY(0 + pTopMargin);
	}
	
	private void layoutAlignParentRight(IEntity pChild, float pRightMargin){
		pChild.layoutX(this.getMeasureWidth() - pRightMargin - pChild.getWidth());
	}
	
	private void layoutAlignParentBottom(IEntity pChild, float pBottomMargin){
		pChild.layoutY(this.getMeasureHeight() - pBottomMargin - pChild.getHeight());
	}
	
	private void layoutCenterVertical(IEntity pChild){
		pChild.layoutY(this.getMeasureHeight() / 2.0f - pChild.getHeight() / 2.0f);
	}
	
	private void layoutCenterHorizontal(IEntity pChild){
		pChild.layoutX(this.getMeasureWidth() / 2.0f - pChild.getWidth() / 2.0f);
	}

	// ===========================================================
	// 布局 参数
	// ===========================================================



	public static class LayoutParams extends EntityLayout.MarginLayoutParams {

		private int[] mRules = new int[VERB_COUNT];

		public LayoutParams() {
			super();
		}

		public LayoutParams(float pWidth, float pHeight) {
			super(pWidth, pHeight);
		}

		public LayoutParams(float left, float top, float right, float bottom) {
			super(left, top, right, bottom);
			// TODO Auto-generated constructor stub
		}

		public void addRule(int verb) {
			mRules[verb] = TRUE;
		}

		public void addRule(int verb, int anchor) {
			mRules[verb] = anchor;
		}

		public int[] getRules() {
			return mRules;
		}
	}

}
