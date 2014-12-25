package com.orange.entity.group;

import android.app.Activity;

import com.orange.engine.Engine;
import com.orange.entity.scene.Scene;
import com.orange.opengl.vbo.VertexBufferObjectManager;
import com.orange.ui.activity.BaseGameActivity;

public class EntityGroup extends BaseEntityGroup {

	private final Scene mScene;
	
	public EntityGroup(Scene pScene) {
		super();
		// TODO Auto-generated constructor stub
		this.mScene = pScene;
	}

	public EntityGroup(float pWidth, float pHeight, Scene pScene) {
		super(pWidth, pHeight);
		// TODO Auto-generated constructor stub
		this.mScene = pScene;
	}

	public EntityGroup(float pX, float pY, float pWidth, float pHeight, Scene pScene) {
		super(pX, pY, pWidth, pHeight);
		// TODO Auto-generated constructor stub
		this.mScene = pScene;
	}

	public Scene getScene() {
		return mScene;
	}
	
	public Activity getActivity(){
		return this.mScene.getActivity();
	}
	
	public Engine getEngine(){
		return ((BaseGameActivity)this.getActivity()).getEngine();
	}
	
	public VertexBufferObjectManager getVertexBufferObjectManager(){
		return this.getEngine().getVertexBufferObjectManager();
	}

}
