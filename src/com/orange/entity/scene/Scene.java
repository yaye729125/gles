package com.orange.entity.scene;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;

import com.orange.content.SceneBundle;
import com.orange.engine.Engine;
import com.orange.entity.Entity;
import com.orange.opengl.vbo.VertexBufferObjectManager;
import com.orange.ui.activity.GameActivity;
import com.orange.util.ReflectionUtils;

public class Scene extends BaseScene implements IScene {

	// ===========================================================
	// 变量
	// ===========================================================
	public static final int RESULT_OK = 0;

	public static final int RESULT_CANCELED = RESULT_OK + 1;

	// ===========================================================
	// 变量
	// ===========================================================
	private Activity mActivity;
	private ISceneLauncher mSceneLauncher;
	private Engine mEngine;
	/** 屏幕宽度 */
	private float mScreenWidth = 0;
	/** 屏幕高度 */
	private float mScreenHeight = 0;

	private SceneBundle bundle;
	private int requestCode = -1;
	private int resultCode = -1;
	private SceneBundle resultBundle = null;

	// ===========================================================
	// 构造
	// ===========================================================

	/**
	 * 
	 * @param pX
	 * @param pY
	 * @param pWidth{@link Entity#SIZE_INFINITE} -1为无穷大
	 * @param pHeight{@link Entity#SIZE_INFINITE} -1为无穷大
	 */
	public Scene(float pX, float pY, float pWidth, float pHeight) {
		super(pX, pY, pWidth, pHeight);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param pWidth {@link Entity#SIZE_INFINITE} -1为无穷大
	 * @param pHeight {@link Entity#SIZE_INFINITE} -1为无穷大
	 */
	public Scene(float pWidth, float pHeight) {
		super(pWidth, pHeight);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 默认位置(0,0)，宽高无穷大
	 */
	public Scene() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 在GameActity中反射调用
	 * 
	 * @param pActivity
	 */
	@SuppressWarnings("unused")
	private void init(Activity pActivity, ISceneLauncher pSceneLauncher, Engine pEngine, SceneBundle bundle, int requestCode) {
		this.mActivity = pActivity;
		this.mSceneLauncher = pSceneLauncher;
		this.mEngine = pEngine;
		this.bundle = bundle;
		this.requestCode = requestCode;

		DisplayMetrics dm = pActivity.getResources().getDisplayMetrics();
		this.mScreenWidth = dm.widthPixels;
		this.mScreenHeight = dm.heightPixels;

		this.onSceneCreate(this.bundle);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public Activity getActivity() {
		return mActivity;
	}

	public Engine getEngine() {
		return mEngine;
	}

	/**
	 * 获取手机屏幕的宽度
	 * @return
	 */
	public float getScreenWidth() {
		return mScreenWidth;
	}

	/**
	 * 获取手机屏幕的高度
	 * @return
	 */
	public float getScreenHeight() {
		return mScreenHeight;
	}

	/**
	 * 获取当前镜头显示范围的宽度
	 * @return
	 */
	public float getCameraWidth() {
		return this.mEngine.getCamera().getWidth();
	}

	/**
	 * 获取当前镜头显示范围的高度
	 * @return
	 */
	public float getCameraHeight() {
		return this.mEngine.getCamera().getHeight();
	}

	public VertexBufferObjectManager getVertexBufferObjectManager() {
		return this.mEngine.getVertexBufferObjectManager();
	}

	// ===========================================================
	// 场景周期
	// ===========================================================

	@Override
	public void onAttached() {
		// TODO Auto-generated method stub
		this.onSceneResume();
		super.onAttached();
	}

	@Override
	public void onDetached() {
		// TODO Auto-generated method stub
		this.onScenePause();
		super.onDetached();
	}

	/**
	 * 周期，创建，在Scene被添加到引擎渲染前执行
	 */
	@Override
	public void onSceneCreate(SceneBundle bundle) {
		// TODO Auto-generated method stub

	}

	/**
	 * 周期，显示，在Scene被添加到引擎渲染后、Activity执行onResume时、Scene重新显示时执行
	 */
	@Override
	public void onSceneResume() {
		// TODO Auto-generated method stub

	}

	/**
	 * 周期，显示，在Scene从引擎渲染删除后资源释放前、Activity执行onPause、Scene被隐藏时执行
	 */
	@Override
	public void onScenePause() {
		// TODO Auto-generated method stub

	}

	/**
	 * 周期，销毁，在Scene完全脱离引擎并释放资源后执行
	 */
	@Override
	public void onSceneDestroy() {
		// TODO Auto-generated method stub

	}

	// ===========================================================
	// Scene 处理
	// ===========================================================

	/**
	 * 开启一个场景具体请参考
	 * {@link GameActivity#startSceneForResult(Class, SceneBundle, int)}
	 * 
	 * @param pSceneCls
	 * @return
	 */
	public Scene startScene(Class<? extends Scene> pSceneCls) {
		return this.mSceneLauncher.startScene(pSceneCls);
	}

	/**
	 * 开启一个场景具体请参考
	 * {@link GameActivity#startSceneForResult(Class, SceneBundle, int)}
	 * 
	 * @param pSceneCls
	 * @param bundle
	 * @return
	 */
	public Scene startScene(Class<? extends Scene> pSceneCls, SceneBundle bundle) {
		return this.mSceneLauncher.startScene(pSceneCls, bundle);
	}

	/**
	 * 开启一个场景具体请参考
	 * {@link GameActivity#startSceneForResult(Class, SceneBundle, int)}
	 * 
	 * @param pSceneCls
	 * @param requestCode
	 * @return
	 */
	public Scene startSceneForResult(Class<? extends Scene> pSceneCls, int requestCode) {
		return this.mSceneLauncher.startSceneForResult(pSceneCls, requestCode);
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
	public Scene startSceneForResult(Class<? extends Scene> pSceneCls, SceneBundle bundle, int requestCode) {
		return this.mSceneLauncher.startSceneForResult(pSceneCls, bundle, requestCode);
	}

	/**
	 * 停止一个场景
	 * 
	 * @param pGameScene
	 */
	public void finish() {
		ReflectionUtils.invokeMethod(this.mSceneLauncher, "finishScene", new Class<?>[] { Scene.class }, new Object[] { this });
	}

	// ===========================================================
	// Scene 数据传递
	// ===========================================================

	public void onSceneResult(int requestCode, int resultCode, SceneBundle data) {

	}

	public void setResult(int resultCode) {
		this.setResult(resultCode, null);
	}

	public void setResult(int resultCode, SceneBundle resultBundle) {
		this.resultCode = resultCode;
		this.resultBundle = resultBundle;
	}

	public int getRequestCode() {
		return requestCode;
	}

	public int getResultCode() {
		return resultCode;
	}

	public SceneBundle getResultBundle() {
		return resultBundle;
	}

	// ===========================================================
	// 系统事件映射
	// ===========================================================

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
			return true;
		}
		return false;
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		}
		return false;
	}

}
