package com.orange.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.orange.content.SceneBundle;
import com.orange.engine.Engine;
import com.orange.engine.options.EngineOptions;
import com.orange.engine.options.PixelPerfectEngineOptions;
import com.orange.entity.scene.Scene;
import com.orange.entity.scene.ISceneLauncher;
import com.orange.entity.scene.group.SceneGroup;
import com.orange.opengl.view.RenderSurfaceView;
import com.orange.ui.activity.GameActivity;
import com.orange.util.ReflectionUtils;

public abstract class GameLauncher extends BaseGameLauncher implements ISceneLauncher{

	// ===========================================================
	// 虚拟方法
	// ===========================================================


	protected abstract PixelPerfectEngineOptions onCreatePixelPerfectEngineOptions();

	protected abstract void onLoadResources();

	protected abstract void onLoadComplete();

	// ===========================================================
	// 变量
	// ===========================================================

	private SceneGroup mSceneGroup;

	private FrameLayout mRootFrameLayout;
	private FrameLayout mRenderLayout;

	private PixelPerfectEngineOptions mPixelPerfectEngineOptions;
	
	
	// ===========================================================
	// 构造方法
	// ===========================================================
	public GameLauncher(Activity pActivity) {
		super(pActivity);
		// TODO Auto-generated constructor stub
	}

	// ===========================================================
	// 继承方法
	// ===========================================================

	@Override
	public void onCreate(Bundle pSavedInstanceState) {
		// TODO Auto-generated method stub
		this.mPixelPerfectEngineOptions = this.onCreatePixelPerfectEngineOptions();
		super.onCreate(pSavedInstanceState);
	}

	@Override
	protected void onSetContentView() {
		// TODO Auto-generated method stub
		ViewGroup.LayoutParams frameLayout_params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
		this.mRootFrameLayout = new FrameLayout(this.getActivity());
		this.mRootFrameLayout.setLayoutParams(frameLayout_params);

		ViewGroup.LayoutParams renderLayout_params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
		this.mRenderLayout = new FrameLayout(this.getActivity());
		this.mRenderLayout.setLayoutParams(renderLayout_params);

		// EditText layout
		ViewGroup.LayoutParams editTextLayout_params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
		EditText editText = new EditText(this.getActivity());
		editText.setLayoutParams(editTextLayout_params);

		ViewGroup.LayoutParams renderSurfaceView_params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
		this.mRenderSurfaceView = new RenderSurfaceView(this.getActivity());
		this.mRenderSurfaceView.setLayoutParams(renderSurfaceView_params);
		this.mRenderSurfaceView.setRenderer(this.mEngine, this);

		this.mRenderLayout.addView(editText);
		this.mRenderLayout.addView(this.mRenderSurfaceView);

		this.mRootFrameLayout.addView(this.mRenderLayout);
		this.getActivity().setContentView(this.mRootFrameLayout);
	}

	public FrameLayout getRootLayout() {
		return this.mRootFrameLayout;
	}

	public FrameLayout getRenderLayout() {
		return mRenderLayout;
	}

	@Override
	public EngineOptions onCreateEngineOptions() {
		// TODO Auto-generated method stub
		return this.mPixelPerfectEngineOptions.createEngineOptions();
	}

	@Override
	public void onCreateResources(final OnCreateResourcesCallback pOnCreateResourcesCallback) throws Exception {
		// TODO Auto-generated method stub
		this.onLoadResources();
		pOnCreateResourcesCallback.onCreateResourcesFinished();
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws Exception {
		// TODO Auto-generated method stub
		pOnCreateSceneCallback.onCreateSceneFinished(new SceneGroup());
	}

	@Override
	public void onPopulateScene(SceneGroup pSceneGroup, OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
		// TODO Auto-generated method stub
		this.mSceneGroup = pSceneGroup;
		pOnPopulateSceneCallback.onPopulateSceneFinished();
		this.onLoadComplete();
	}

	// ===========================================================
	// 场景操作
	// ===========================================================

	/**
	 * 开启一个场景具体请参考
	 * {@link GameActivity#startSceneForResult(Class, SceneBundle, int)}
	 * 
	 * @param pSceneCls
	 * @return
	 */
	@Override
	public Scene startScene(Class<? extends Scene> pSceneCls) {
		return startScene(pSceneCls, null);
	}

	/**
	 * 开启一个场景具体请参考
	 * {@link GameActivity#startSceneForResult(Class, SceneBundle, int)}
	 * 
	 * @param pSceneCls
	 * @param bundle
	 * @return
	 */
	@Override
	public Scene startScene(Class<? extends Scene> pSceneCls, SceneBundle bundle) {
		return this.startSceneForResult(pSceneCls, bundle, -1);
	}

	/**
	 * 开启一个场景具体请参考
	 * {@link GameActivity#startSceneForResult(Class, SceneBundle, int)}
	 * 
	 * @param pSceneCls
	 * @param requestCode
	 * @return
	 */
	@Override
	public Scene startSceneForResult(Class<? extends Scene> pSceneCls, int requestCode) {
		return this.startSceneForResult(pSceneCls, null, -1);
	}

	/**
	 * 开启一个场景
	 * 
	 * @param pSceneCls
	 *            场景类
	 * @param bundle
	 *            数据
	 * @param requestCode
	 *            请求代码，用于数据返回标志，不能为负数
	 * @return
	 */
	@Override
	public Scene startSceneForResult(Class<? extends Scene> pSceneCls, SceneBundle bundle, int requestCode) {
		Scene gameScene = ReflectionUtils.newInstance(pSceneCls);
		Class<?>[] parameterTypes = { Activity.class, ISceneLauncher.class, Engine.class, SceneBundle.class, int.class };
		Object[] argParam = { this.getActivity(), this, this.getEngine(), bundle, requestCode };
		ReflectionUtils.invokeMethod(gameScene, "init", parameterTypes, argParam);
		this.mSceneGroup.attachScene(gameScene);
		int sceneCount = this.mSceneGroup.getScenerCount();
		if (sceneCount > 1) {
			for (int i = sceneCount - 2; i >= 0; i--) {
				Scene bottomScene = (Scene) this.mSceneGroup.getSceneByIndex(i);
				if (bottomScene.isVisible()) {
					bottomScene.onScenePause();
					bottomScene.setVisible(false);
				}
			}
		}
		return gameScene;
	}

	/**
	 * 停止一个场景
	 * 
	 * @param pGameScene
	 */
	@Override
	public void finishScene(Scene pGameScene) {
		int sceneCount = this.mSceneGroup.getScenerCount();
		if (sceneCount > 1) {
			this.mSceneGroup.detachScene(pGameScene);
			pGameScene.onSceneDestroy();
			Scene lastScene = (Scene) this.mSceneGroup.getSceneByIndex(sceneCount - 2);
			lastScene.onSceneResult(pGameScene.getRequestCode(), pGameScene.getResultCode(), pGameScene.getResultBundle());
			lastScene.onSceneResume();
			lastScene.setVisible(true);
		}
	}

	// ===========================================================
	// 系统事件映射
	// ===========================================================

	@Override
	public
	synchronized void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (this.mSceneGroup != null) {
			int sceneCount = this.mSceneGroup.getScenerCount();
			if (sceneCount > 0) {
				for (int i = sceneCount - 1; i >= 0; i--) {
					Scene bottomScene = (Scene) this.mSceneGroup.getSceneByIndex(i);
					if (bottomScene.isVisible()) {
						bottomScene.onSceneResume();
					}
				}
			}
		}
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (this.mSceneGroup != null) {
			int sceneCount = this.mSceneGroup.getScenerCount();
			if (sceneCount > 0) {
				for (int i = sceneCount - 1; i >= 0; i--) {
					Scene bottomScene = (Scene) this.mSceneGroup.getSceneByIndex(i);
					if (bottomScene.isVisible()) {
						bottomScene.onScenePause();
					}
				}
			}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (this.mSceneGroup != null) {
			Scene lastScene = (Scene) this.mSceneGroup.getLastScene();
			if (lastScene != null) {
				return lastScene.onKeyDown(keyCode, event);
			} else {
				return super.onKeyDown(keyCode, event);
			}
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (this.mSceneGroup != null) {
			Scene lastScene = (Scene) this.mSceneGroup.getLastScene();
			if (lastScene != null) {
				return lastScene.onKeyUp(keyCode, event);
			} else {
				return super.onKeyUp(keyCode, event);
			}
		} else {
			return super.onKeyUp(keyCode, event);
		}
	}

	public void stopGame() {
		if (this.prepareFinish()) {
			this.finish();
		}
	}

	/**
	 * 准备关闭，在此可以做对话框能功能
	 * 
	 * @return 返回true则继续执行finish，返回false终止执行
	 */
	protected boolean prepareFinish() {
		return true;
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
	}

	public SceneGroup getSceneGroup() {
		return mSceneGroup;
	}

}
