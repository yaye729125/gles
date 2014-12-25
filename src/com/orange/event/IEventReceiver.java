package com.orange.event;

/**
 * 事件接收接口
 * @author OrangeGame <OGEngine@orangegame.cn>
 *
 */
public interface IEventReceiver {

	/**
	 * 事件接收广播
	 * @param eventIntent
	 * @return 返回true终止广播，flase继续发送广播
	 */
	public boolean onReceive(EventSource pEventSource);
	
}
