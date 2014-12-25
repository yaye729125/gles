package com.orange.engine.handler.timer;

import com.orange.engine.handler.IUpdateHandler;

/**
 * timerHandler 处理更新
 * (c) OrangeGame 2012 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class TimerHandler implements IUpdateHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	/**计时器每次间隔时间*/
	private float mTimerSeconds;
    /**计时器间隔时间记录值*/
	private float mTimerSecondsElapsed;
	/**是否触发*/
	private boolean mTimerCallbackTriggered;
	/**计时器触发callback*/
	protected final ITimerCallback mTimerCallback;
	/**是否自动重置*/
	private boolean mAutoReset;

	// ===========================================================
	// Constructors
	// ===========================================================
	/**
	 * 构建计时器处理
	 * @param pTimerSeconds 每次响应间隔时间
	 * @param pTimerCallback 计时器触发callback
	 */
	public TimerHandler(final float pTimerSeconds, final ITimerCallback pTimerCallback) {
		this(pTimerSeconds, false, pTimerCallback);
	}

	public TimerHandler(final float pTimerSeconds, final boolean pAutoReset, final ITimerCallback pTimerCallback) {
		if(pTimerSeconds <= 0){
			throw new IllegalStateException("pTimerSeconds must be > 0!");
		}

		this.mTimerSeconds = pTimerSeconds;
		this.mAutoReset = pAutoReset;
		this.mTimerCallback = pTimerCallback;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public boolean isAutoReset() {
		return this.mAutoReset;
	}

	public void setAutoReset(final boolean pAutoReset) {
		this.mAutoReset = pAutoReset;
	}

	public void setTimerSeconds(final float pTimerSeconds) {
		if(pTimerSeconds <= 0){
			throw new IllegalStateException("pTimerSeconds must be > 0!");
		}

		this.mTimerSeconds = pTimerSeconds;
	}

	public float getTimerSeconds() {
		return this.mTimerSeconds;
	}

	public float getTimerSecondsElapsed() {
		return this.mTimerSecondsElapsed;
	}
	
	public boolean isTimerCallbackTriggered() {
		return this.mTimerCallbackTriggered;
	}

	public void setTimerCallbackTriggered(boolean pTimerCallbackTriggered) {
		this.mTimerCallbackTriggered = pTimerCallbackTriggered;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onUpdate(final float pSecondsElapsed) {
		if(this.mAutoReset) {
			this.mTimerSecondsElapsed += pSecondsElapsed;
			while(this.mTimerSecondsElapsed >= this.mTimerSeconds) {
				this.mTimerSecondsElapsed -= this.mTimerSeconds;
				this.mTimerCallback.onTimePassed(this);
			}
		} else {
			if(!this.mTimerCallbackTriggered) {
				this.mTimerSecondsElapsed += pSecondsElapsed;
				if(this.mTimerSecondsElapsed >= this.mTimerSeconds) {
					this.mTimerCallbackTriggered = true;
					this.mTimerCallback.onTimePassed(this);
				}
			}
		}
	}

	@Override
	public void reset() {
		this.mTimerCallbackTriggered = false;
		this.mTimerSecondsElapsed = 0;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
