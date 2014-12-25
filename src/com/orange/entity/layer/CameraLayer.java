package com.orange.entity.layer;

import com.orange.engine.camera.Camera;
import com.orange.entity.IEntity;
import com.orange.entity.scene.Scene;
import com.orange.entity.shape.RectangularShape;
import com.orange.input.touch.TouchEvent;
import com.orange.opengl.util.GLState;
import com.orange.opengl.vbo.VertexBufferObjectManager;
import com.orange.ui.activity.GameActivity;

public class CameraLayer extends Layer {

	// ===========================================================
	// 变量
	// ===========================================================
	
	private Camera mCamera;

	// ===========================================================
	// 构造
	// ===========================================================
	
	public CameraLayer(Camera pCamera, Scene pGameScene) {
		super(pGameScene);
		this.mCamera = pCamera;
	}

	// ===========================================================
	// Getter And Setter
	// ===========================================================

	public Camera getCamera() {
		return mCamera;
	}

	public void setCamera(Camera mCamera) {
		this.mCamera = mCamera;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		// TODO Auto-generated method stub
		if (this.mCamera == null) {
			return false;
		} else {
			this.mCamera.convertSceneToCameraSceneTouchEvent(pSceneTouchEvent);

			final boolean handled = super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);

			if (handled) {
				return true;
			} else {
				this.mCamera.convertCameraSceneToSceneTouchEvent(pSceneTouchEvent);
				return false;
			}
		}
	}

	public void onApplyMatrix(final GLState pGLState, final Camera pCamera) {
		this.mCamera.onApplyCameraSceneMatrix(pGLState);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void centerShapeInCamera(final RectangularShape pRectangularShape) {
		final Camera camera = this.mCamera;
		pRectangularShape.setPosition((camera.getWidth() - pRectangularShape.getWidth()) * 0.5f, (camera.getHeight() - pRectangularShape.getHeight()) * 0.5f);
	}

	public void centerShapeInCameraHorizontally(final RectangularShape pRectangularShape) {
		pRectangularShape.setPosition((this.mCamera.getWidth() - pRectangularShape.getWidth()) * 0.5f, pRectangularShape.getY());
	}

	public void centerShapeInCameraVertically(final RectangularShape pRectangularShape) {
		pRectangularShape.setPosition(pRectangularShape.getX(), (this.mCamera.getHeight() - pRectangularShape.getHeight()) * 0.5f);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
