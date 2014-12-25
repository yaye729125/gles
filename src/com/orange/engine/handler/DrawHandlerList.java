package com.orange.engine.handler;

import com.orange.engine.camera.Camera;
import com.orange.opengl.util.GLState;
import com.orange.util.adt.list.SmartList;

/**
 * 绘画handler列表
 * (c) OrangeGame 2012
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class DrawHandlerList extends SmartList<IDrawHandler> implements IDrawHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = 1767324757143199934L;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public DrawHandlerList() {

	}

	public DrawHandlerList(final int pCapacity) {
		super(pCapacity);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onDraw(final GLState pGLState, final Camera pCamera) {
		final int handlerCount = this.size();
		for(int i = handlerCount - 1; i >= 0; i--) {
			this.get(i).onDraw(pGLState, pCamera);
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
