package com.orange.entity.layout;

import com.orange.entity.Entity;
import com.orange.entity.scene.Scene;

public class FrameEntityLayout extends EntityLayout {

	public FrameEntityLayout(float pX, float pY, LayoutParams pLayoutParams, Scene pScene) {
		super(pX, pY, pLayoutParams, pScene);
		// TODO Auto-generated constructor stub
	}

	public FrameEntityLayout(LayoutParams pLayoutParams, Scene pScene) {
		super(pLayoutParams, pScene);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onLayout() {
		// TODO Auto-generated method stub
		super.onLayout();
		this.layoutFrame();
		this.setSize();
	}

	protected void layoutFrame() {
		int childCount = this.getChildCount();
		for (int i = 0; i < childCount; i++) {
			Entity child = (Entity) this.getChildByIndex(i);
			if (child != null) {
				child.layout(0.0f, 0.0f);
			}
		}
	}

}
