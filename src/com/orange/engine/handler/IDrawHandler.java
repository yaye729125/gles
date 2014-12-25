package com.orange.engine.handler;

import com.orange.engine.camera.Camera;
import com.orange.opengl.util.GLState;


/**
 * 绘画handler接口
 * (c) OrangeGame 2012 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IDrawHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	/**
	 * 绘制
	 * @param pGLState
	 * @param pCamera
	 */
	public void onDraw(final GLState pGLState, final Camera pCamera);
}
