package com.orange.engine.handler.runnable;

import java.util.ArrayList;

import com.orange.engine.handler.IUpdateHandler;

/**
 * Runnanle处理
 * (c) OrangeGame 2012 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class RunnableHandler implements IUpdateHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final ArrayList<Runnable> mRunnables = new ArrayList<Runnable>();

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public synchronized void onUpdate(final float pSecondsElapsed) {
		final ArrayList<Runnable> runnables = this.mRunnables;
		final int runnableCount = runnables.size();
		for(int i = runnableCount - 1; i >= 0; i--) {
			runnables.remove(i).run();
		}
	}

	@Override
	public synchronized void reset() {
		this.mRunnables.clear();
	}

	// ===========================================================
	// Methods
	// ===========================================================
	/**
	 * 传递Runnable 类似Handler.post()用法
	 * @param pRunnable
	 */
	public synchronized void postRunnable(final Runnable pRunnable) {
		this.mRunnables.add(pRunnable);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
