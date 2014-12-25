package com.orange.entity.group;

import com.orange.entity.Entity;
import com.orange.entity.scene.Scene;

public class WrapContentEntityGroup extends EntityGroup{

	public WrapContentEntityGroup(float pX, float pY, float pWidth, float pHeight, Scene pScene) {
		super(pX, pY, pWidth, pHeight, pScene);
		// TODO Auto-generated constructor stub
	}

	public WrapContentEntityGroup(float pWidth, float pHeight, Scene pScene) {
		super(pWidth, pHeight, pScene);
		// TODO Auto-generated constructor stub
	}

	public WrapContentEntityGroup(Scene pScene) {
		super(pScene);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isNeedLayout() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void onLayout() {
		// TODO Auto-generated method stub
		super.onLayout();
		
		float maxRight = 0.0f;
		float maxBottom = 0.0f;
		int count = this.getChildCount();
		for (int i = 0; i < count; i++) {
			final Entity child = (Entity) this.getChildByIndex(i);
			if (child != null) {
				float right = child.getRightX();
				float bottom = child.getBottomY();
				if (right > maxRight) {
					maxRight = right;
				}
				if (bottom > maxBottom) {
					maxBottom = bottom;
				}
			}
		}
		this.setSize(maxRight, maxBottom);
	}

	
}
