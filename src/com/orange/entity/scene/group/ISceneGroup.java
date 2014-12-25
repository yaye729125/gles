package com.orange.entity.scene.group;

import com.orange.entity.scene.Scene;
import com.orange.input.touch.TouchEvent;

public interface ISceneGroup {
	
	public int getScenerCount();
	
	public void attachScene(Scene pScene);
	
	public void attachScene(Scene pScene, int pIndex);
	
	public boolean detachScene(Scene pScene);
	
	public Scene detachScene(final int pTag);
	
	public void detachScenes();
	
	public Scene getLastScene();
	
	public Scene getFirstScene();
	
	public Scene getSceneByIndex(int pIndex);
	
	public Scene getSceneByTag(int pTag);
	
	public boolean onSceneTouchEvent(TouchEvent pSceneTouchEvent);
}
