package com.orange.entity.layer;

import com.orange.entity.group.EntityGroup;
import com.orange.entity.scene.Scene;

public class Layer extends EntityGroup {

	// ===========================================================
	// 变量
	// ===========================================================

	// ===========================================================
	// 构造
	// ===========================================================
	public Layer(float pX, float pY, float pWidth, float pHeight, Scene pScene) {
		super(pX, pY, pWidth, pHeight, pScene);
		// TODO Auto-generated constructor stub
	}

	public Layer(float pWidth, float pHeight, Scene pScene) {
		super(pWidth, pHeight, pScene);
		// TODO Auto-generated constructor stub
	}

	public Layer(Scene pScene) {
		super(pScene);
		// TODO Auto-generated constructor stub
	}

	// ===========================================================
	// 父类
	// ===========================================================

}
