package com.orange.event;

/**
 * 事件源接口
 * @author OrangeGame <OGEngine@orangegame.cn>
 *
 */
public interface IEventSource {

	/**
	 * 事件名
	 * @return
	 */
	public  String getEventName();
	/**
	 * 事件描述
	 * @return
	 */
	public  String getEventMessage();
	
}
