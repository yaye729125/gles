package com.orange.ui;

import com.orange.engine.Engine;
import com.orange.engine.options.EngineOptions;
import com.orange.entity.scene.BaseScene;
import com.orange.entity.scene.group.SceneGroup;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IGameInterface {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public EngineOptions onCreateEngineOptions();
	public Engine onCreateEngine(final EngineOptions pEngineOptions);

	public void onCreateResources(final OnCreateResourcesCallback pOnCreateResourcesCallback) throws Exception;
	public void onCreateScene(final OnCreateSceneCallback pOnCreateSceneCallback) throws Exception;
	public void onPopulateScene(final SceneGroup pSceneGroup, final OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception;

	public void onReloadResources() throws Exception;
	public void onDestroyResources() throws Exception;

	public void onGameCreated();
	public void onResumeGame();
	public void onPauseGame();
	public void onGameDestroyed();

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	public static interface OnCreateResourcesCallback {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================

		public void onCreateResourcesFinished();
	}

	public static interface OnCreateSceneCallback {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================

		public void onCreateSceneFinished(final SceneGroup pSceneGroup);
	}

	public static interface OnPopulateSceneCallback {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================

		public void onPopulateSceneFinished();
	}
}
