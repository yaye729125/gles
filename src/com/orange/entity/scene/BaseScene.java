package com.orange.entity.scene;

import android.util.Log;

import com.orange.engine.camera.Camera;
import com.orange.engine.handler.runnable.RunnableHandler;
import com.orange.entity.group.BaseEntityGroup;
import com.orange.entity.layout.EntityLayout;
import com.orange.input.touch.TouchEvent;
import com.orange.opengl.util.GLState;
import com.orange.util.Constants;
import com.orange.util.debug.Debug;

/**
 * (c) OrangeGame 2012 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class BaseScene extends BaseEntityGroup implements IBaseScene {
	// ===========================================================
	// Constants
	// ===========================================================
	// ===========================================================
	// Fields
	// ===========================================================

	
	private float mSecondsElapsedTotal;

	private final RunnableHandler mRunnableHandler = new RunnableHandler();

	// ===========================================================
	// Constructors
	// ===========================================================

	public BaseScene(float pX, float pY, float pWidth, float pHeight) {
		super(pX, pY, pWidth, pHeight);
		// TODO Auto-generated constructor stub
	}

	public BaseScene(float pWidth, float pHeight) {
		super(pWidth, pHeight);
		// TODO Auto-generated constructor stub
	}
	
	public BaseScene() {
		super();
		// TODO Auto-generated constructor stub
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================


	public float getSecondsElapsedTotal() {
		return this.mSecondsElapsedTotal;
	}

	// ===========================================================
	// 父类处理
	// ===========================================================

	@Override
	public void onManagedDraw(GLState pGLState, Camera pCamera) {
		// TODO Auto-generated method stub
		pGLState.pushProjectionGLMatrix();

		this.onApplyMatrix(pGLState, pCamera);
		pGLState.loadModelViewGLMatrixIdentity();

		super.onManagedDraw(pGLState, pCamera);

		pGLState.popProjectionGLMatrix();
	}

	protected void onApplyMatrix(final GLState pGLState, final Camera pCamera) {
		pCamera.onApplySceneMatrix(pGLState);
	}

	@Override
	public void onManagedUpdate(final float pSecondsElapsed) {
		this.mSecondsElapsedTotal += pSecondsElapsed;

		this.mRunnableHandler.onUpdate(pSecondsElapsed);

		super.onManagedUpdate(pSecondsElapsed);
	}

	@Override
	public void reset() {
		super.reset();
	}

	// ===========================================================
	// 事件处理
	// ===========================================================

	public boolean onSceneTouchEvent(final TouchEvent pSceneTouchEvent) {
		final float sceneTouchEventX = pSceneTouchEvent.getX();
		final float sceneTouchEventY = pSceneTouchEvent.getY();
		final float[] touchAreaLocalCoordinates = this.convertSceneToLocalCoordinates(sceneTouchEventX, sceneTouchEventY);
		final float touchAreaLocalX = touchAreaLocalCoordinates[Constants.VERTEX_INDEX_X];
		final float touchAreaLocalY = touchAreaLocalCoordinates[Constants.VERTEX_INDEX_Y];
		final boolean handledSelf = this.onTouch(pSceneTouchEvent, touchAreaLocalX, touchAreaLocalY);
		return true;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void postRunnable(final Runnable pRunnable) {
		this.mRunnableHandler.postRunnable(pRunnable);
	}


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
