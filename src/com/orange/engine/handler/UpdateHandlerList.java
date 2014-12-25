package com.orange.engine.handler;

import com.orange.util.adt.list.SmartList;

/**
 * 更新Handler列表
 * (c) OrangeGame 2012 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class UpdateHandlerList extends SmartList<IUpdateHandler> implements IUpdateHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = -8842562717687229277L;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public UpdateHandlerList() {

	}

	public UpdateHandlerList(final int pCapacity) {
		super(pCapacity);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onUpdate(final float pSecondsElapsed) {
		final int handlerCount = this.size();
		for(int i = handlerCount - 1; i >= 0; i--) {
			IUpdateHandler iUpdateHandler = this.get(i);
			if (iUpdateHandler != null) {
				iUpdateHandler.onUpdate(pSecondsElapsed);
			}
		}
	}

	@Override
	public void reset() {
		final int handlerCount = this.size();
		for(int i = handlerCount - 1; i >= 0; i--) {
			IUpdateHandler iUpdateHandler = this.get(i);
			if (iUpdateHandler != null) {
				iUpdateHandler.reset();
			}
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
