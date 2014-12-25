package com.orange.entity.util;

import com.orange.BuildConfig;
import com.orange.engine.handler.IUpdateHandler;
import com.orange.util.debug.Debug;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class FrameCountCrasher implements IUpdateHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private int mFramesLeft;

	private final float[] mFrameLengths;

	// ===========================================================
	// Constructors
	// ===========================================================

	public FrameCountCrasher(final int pFrameCount) {
		this.mFramesLeft = pFrameCount;
		this.mFrameLengths = new float[pFrameCount];
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onUpdate(final float pSecondsElapsed) {
		this.mFramesLeft--;

		final float[] frameLengths = this.mFrameLengths;
		if(this.mFramesLeft >= 0) {
			frameLengths[this.mFramesLeft] = pSecondsElapsed;
		} else {
			if(BuildConfig.DEBUG) {
				for(int i = frameLengths.length - 1; i >= 0; i--) {
					Debug.d("Elapsed: " + frameLengths[i]);
				}
			}

			throw new RuntimeException();
		}
	}

	@Override
	public void reset() {

	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
