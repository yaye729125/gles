package com.orange.entity.scene;

import com.orange.content.SceneBundle;

import android.view.KeyEvent;

public interface IScene extends IBaseScene{

	public void onSceneCreate(SceneBundle bundle);
	public void onSceneResume();
	public void onScenePause();
	public void onSceneDestroy();
	
	public boolean onKeyDown(int keyCode, KeyEvent event);
	public boolean onKeyUp(int keyCode, KeyEvent event);
	
}
