package com.orange.engine;

import com.orange.engine.options.EngineOptions;
import com.orange.util.time.TimeConstants;

/**
 * 限制帧频更新引擎：允许我们设置引擎每秒运行的帧数限制,迫使游戏以最高的限制帧数去更新
 * (c) OrangeGame 2012 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class LimitedFPSEngine extends Engine {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final long mPreferredFrameLengthNanoseconds;

	// ===========================================================
	// Constructors
	// ===========================================================

	public LimitedFPSEngine(final EngineOptions pEngineOptions, final int pFramesPerSecond) {
		super(pEngineOptions);
		this.mPreferredFrameLengthNanoseconds = TimeConstants.NANOSECONDS_PER_SECOND / pFramesPerSecond;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onUpdate(final long pNanosecondsElapsed) throws InterruptedException {
		final long preferredFrameLengthNanoseconds = this.mPreferredFrameLengthNanoseconds;
		final long deltaFrameLengthNanoseconds = preferredFrameLengthNanoseconds - pNanosecondsElapsed;

		if(deltaFrameLengthNanoseconds <= 0) {
			super.onUpdate(pNanosecondsElapsed);
		} else {
			final int sleepTimeMilliseconds = (int) (deltaFrameLengthNanoseconds / TimeConstants.NANOSECONDS_PER_MILLISECOND);

			Thread.sleep(sleepTimeMilliseconds);
			super.onUpdate(pNanosecondsElapsed + deltaFrameLengthNanoseconds);
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
