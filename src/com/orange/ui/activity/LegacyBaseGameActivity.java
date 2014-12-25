package com.orange.ui.activity;

import com.orange.engine.Engine;
import com.orange.engine.options.EngineOptions;
import com.orange.entity.scene.BaseScene;
import com.orange.entity.scene.group.SceneGroup;


/**
 * This class exists to provide exact backward naming compatibility to older versions of {@link BaseGameActivity}.
 * Please consider actually switching to the new {@link BaseGameActivity}.
 *
 * (c) OrangeGame 2012
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
@Deprecated
public abstract class LegacyBaseGameActivity extends BaseGameActivity {
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

	protected abstract Engine onLoadEngine();
	protected abstract void onLoadResources();
	protected abstract void onUnloadResources();
	protected abstract SceneGroup onLoadSceneGroup();
	protected abstract SceneGroup onLoadComplete();

	@Override
	public final EngineOptions onCreateEngineOptions() {
		return null;
	}

	@Override
	public final Engine onCreateEngine(final EngineOptions pEngineOptions) {
		return this.onLoadEngine();
	}

	@Override
	public final void onCreateResources(final OnCreateResourcesCallback pOnCreateResourcesCallback) throws Exception {
		this.onLoadResources();

		pOnCreateResourcesCallback.onCreateResourcesFinished();
	}

	@Override
	public final void onCreateScene(final OnCreateSceneCallback pOnCreateSceneCallback) throws Exception {
		final SceneGroup sceneGroup = this.onLoadSceneGroup();

		pOnCreateSceneCallback.onCreateSceneFinished(sceneGroup);
	}

	@Override
	public final void onPopulateScene(final SceneGroup pSceneGroup, final OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
		pOnPopulateSceneCallback.onPopulateSceneFinished();
	}

	@Override
	public final void onDestroyResources() throws Exception {
		super.onDestroyResources();

		this.onUnloadResources();
	}

	@Override
	public synchronized void onGameCreated() {
		super.onGameCreated();

		this.onLoadComplete();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
