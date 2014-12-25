package com.orange.ui.activity;

import com.orange.entity.scene.group.SceneGroup;
import com.orange.ui.IGameInterface;


/**
 * This class exists so that the callback parameters of the methods in {@link IGameInterface} get called automatically.
 * 
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public abstract class SimpleBaseGameActivity extends BaseGameActivity {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	protected abstract void onCreateResources();
	protected abstract SceneGroup onCreateSceneGroup();

	@Override
	public final void onCreateResources(final OnCreateResourcesCallback pOnCreateResourcesCallback) throws Exception {
		this.onCreateResources();

		pOnCreateResourcesCallback.onCreateResourcesFinished();
	}

	@Override
	public final void onCreateScene(final OnCreateSceneCallback pOnCreateSceneCallback) throws Exception {
		final SceneGroup sceneGroup = this.onCreateSceneGroup();

		pOnCreateSceneCallback.onCreateSceneFinished(sceneGroup);
	}

	@Override
	public final void onPopulateScene(final SceneGroup pSceneGroup, final OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
		pOnPopulateSceneCallback.onPopulateSceneFinished();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
