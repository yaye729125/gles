package com.orange.event;

/**
 * 事件接收
 * @author OrangeGame <OGEngine@orangegame.cn>
 *
 */
public abstract class EventReceiver implements IEventReceiver {

	// ===========================================================
	// 变量
	// ===========================================================
	/**
	 * 动作名
	 */
	private String mAction = "";

	// ===========================================================
	// 构造
	// ===========================================================
	/**
	 * 
	 * @param pAction 动作名
	 */
	public EventReceiver(String pAction) {
		this.mAction = pAction;
	}

	// ===========================================================
	// Getter And Setter
	// ===========================================================
	/**
	 * 获取动作名
	 * @return
	 */
	public String getAction() {
		return mAction;
	}

	/**
	 * 设置动作名
	 * @param pAction
	 */
	public void setAction(String pAction) {
		this.mAction = pAction;
	}

}
