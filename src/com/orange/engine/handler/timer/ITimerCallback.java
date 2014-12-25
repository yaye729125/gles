package com.orange.engine.handler.timer;

/**
 * 计时器触发回调
 * (c) OrangeGame 2012 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface ITimerCallback {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	/**
	 * 计时器事件处理
	 * @param pTimerHandler 计时器处理
	 */
	public void onTimePassed(final TimerHandler pTimerHandler);
}
