package com.orange.entity.layout;

import com.orange.entity.group.EntityGroup;
import com.orange.entity.scene.Scene;

public class BaseEntityLayout extends EntityGroup {

	public BaseEntityLayout(float pX, float pY, float pWidth, float pHeight, Scene pScene) {
		super(pX, pY, pWidth, pHeight, pScene);
		// TODO Auto-generated constructor stub
		this.init();
	}

	public BaseEntityLayout(float pWidth, float pHeight, Scene pScene) {
		super(pWidth, pHeight, pScene);
		// TODO Auto-generated constructor stub
		this.init();
	}

	public BaseEntityLayout(Scene pScene) {
		super(pScene);
		// TODO Auto-generated constructor stub
		this.init();
	}
	
	private void init(){
		this.setCullingEnabled(true);
	}

	// ===========================================================
	// 布局处理
	// ===========================================================

	@Override
	public boolean isNeedLayout() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isNeedNotifyDrawRectChanged() {
		// TODO Auto-generated method stub
		return true;
	}

}
