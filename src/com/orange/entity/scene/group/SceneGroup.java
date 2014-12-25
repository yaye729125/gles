package com.orange.entity.scene.group;

import com.orange.engine.camera.Camera;
import com.orange.entity.Entity;
import com.orange.entity.IEntity;
import com.orange.entity.IEntityComparator;
import com.orange.entity.IEntityMatcher;
import com.orange.entity.IEntityParameterCallable;
import com.orange.entity.ZIndexSorter;
import com.orange.entity.group.BaseEntityGroup;
import com.orange.entity.group.EntityGroup;
import com.orange.entity.group.IEntityGroup;
import com.orange.entity.layer.Layer;
import com.orange.entity.scene.BaseScene;
import com.orange.entity.scene.Scene;
import com.orange.entity.scene.background.Background;
import com.orange.entity.scene.background.IBackground;
import com.orange.input.touch.TouchEvent;
import com.orange.opengl.util.GLState;
import com.orange.util.adt.list.SmartList;
import com.orange.util.call.ParameterCallable;
import com.orange.util.color.Color;
import com.orange.util.debug.Debug;

/**
 * 场景群组类，
 * @author OrangeGame <OGEngine@orangegame.cn>
 *
 */
public class SceneGroup extends Entity implements ISceneGroup{
	

	// ===========================================================
	// 变量
	// ===========================================================
	private final BaseEntityGroup mSceneGroup;
	private IBackground mBackground = new Background(Color.BLACK);

	// ===========================================================
	// 构造
	// ===========================================================
	
	public SceneGroup() {
		super();
		// TODO Auto-generated constructor stub
		this.mSceneGroup = new BaseEntityGroup(0, 0);
	}

	// ===========================================================
	// 父类处理
	// ===========================================================
	
	@Override
	protected void onManagedDraw(GLState pGLState, Camera pCamera) {
		// TODO Auto-generated method stub
		this.drawBackground(pGLState, pCamera);
		super.onManagedDraw(pGLState, pCamera);
		this.mSceneGroup.onManagedDraw(pGLState, pCamera);
	}
	
	private void drawBackground(GLState pGLState, Camera pCamera) {
		pGLState.pushProjectionGLMatrix();
		
		pCamera.onApplySceneBackgroundMatrix(pGLState);
		pGLState.loadModelViewGLMatrixIdentity();

		//背景绘制附带了glclear，如果需要去掉背景绘制，必须把Background#onDraw的内容复制过来。
		this.mBackground.onDraw(pGLState, pCamera);

		pGLState.popProjectionGLMatrix();
	}
	
	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		// TODO Auto-generated method stub
		super.onManagedUpdate(pSecondsElapsed);
		this.mSceneGroup.onManagedUpdate(pSecondsElapsed);
	}
	
	// ===========================================================
	// 事件处理
	// ===========================================================
	

	@Override
	public boolean onSceneTouchEvent(TouchEvent pSceneTouchEvent) {
		// TODO Auto-generated method stub
		int sceneCount = this.getScenerCount();
		for (int i = sceneCount - 1; i >= 0; i--) {
			Scene scene = this.getSceneByIndex(i);
			if (scene != null) {
				final boolean handled = scene.onSceneTouchEvent(pSceneTouchEvent);
				if (handled) {
					break;
				}
			}
		}
		return true;
	}

	// ===========================================================
	// 场景处理
	// ===========================================================
	
	@Override
	public int getScenerCount() {
		// TODO Auto-generated method stub
		return this.mSceneGroup.getChildCount();
	}

	@Override
	public void attachScene(Scene pScene) {
		// TODO Auto-generated method stub
		this.mSceneGroup.attachChild(pScene);
	}

	@Override
	public void attachScene(Scene pScene, int pIndex) {
		// TODO Auto-generated method stub
		this.mSceneGroup.attachChild(pScene, pIndex);
	}

	@Override
	public boolean detachScene(Scene pScene) {
		// TODO Auto-generated method stub
		return this.mSceneGroup.detachChild(pScene);
	}

	@Override
	public Scene detachScene(int pTag) {
		// TODO Auto-generated method stub
		return (Scene) this.mSceneGroup.detachChild(pTag);
	}

	@Override
	public void detachScenes() {
		// TODO Auto-generated method stub
		this.mSceneGroup.detachChildren();
	}

	@Override
	public Scene getLastScene() {
		// TODO Auto-generated method stub
		return (Scene) this.mSceneGroup.getLastChild();
	}

	@Override
	public Scene getFirstScene() {
		// TODO Auto-generated method stub
		return (Scene) this.mSceneGroup.getFirstChild();
	}

	@Override
	public Scene getSceneByIndex(int pIndex) {
		// TODO Auto-generated method stub
		return (Scene) this.mSceneGroup.getChildByIndex(pIndex);
	}

	@Override
	public Scene getSceneByTag(int pTag) {
		// TODO Auto-generated method stub
		return (Scene) this.mSceneGroup.getChildByTag(pTag);
	}


}
