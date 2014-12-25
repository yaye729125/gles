package com.orange.entity.scene;

import com.orange.content.SceneBundle;
import com.orange.ui.activity.GameActivity;

public interface ISceneLauncher {

	/**
	 * 开启一个场景具体请参考
	 * {@link GameActivity#startSceneForResult(Class, SceneBundle, int)}
	 * 
	 * @param pSceneCls
	 * @return
	 */
	public Scene startScene(Class<? extends Scene> pSceneCls);

	/**
	 * 开启一个场景具体请参考
	 * {@link GameActivity#startSceneForResult(Class, SceneBundle, int)}
	 * 
	 * @param pSceneCls
	 * @param bundle
	 * @return
	 */
	public Scene startScene(Class<? extends Scene> pSceneCls, SceneBundle bundle);

	/**
	 * 开启一个场景具体请参考
	 * {@link GameActivity#startSceneForResult(Class, SceneBundle, int)}
	 * 
	 * @param pSceneCls
	 * @param requestCode
	 * @return
	 */
	public Scene startSceneForResult(Class<? extends Scene> pSceneCls, int requestCode);

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
	public Scene startSceneForResult(Class<? extends Scene> pSceneCls, SceneBundle bundle, int requestCode);

	/**
	 * 停止一个场景
	 * 
	 * @param pGameScene
	 */
	public void finishScene(Scene pGameScene);
	
}
