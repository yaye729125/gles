package com.orange.event;

import java.util.HashMap;
import java.util.Map;

import com.orange.content.DataBundle;

/**
 * 事件源
 * @author OrangeGame <OGEngine@orangegame.cn>
 *
 */
public class EventSource extends DataBundle implements IEventSource{
	
	// ===========================================================
	// 变量
	// ===========================================================

	private static final String DEFAULT_KEY = "daufault_key";
	
	/**
	 * 动作名
	 */
	private String mAction = "";

	
	// ===========================================================
	// 构造
	// ===========================================================
	/**
	 * 
	 * @param pAction 唯一动作名
	 */
	public EventSource(String pAction){
		this.init(pAction, null);
	}
	
	/**
	 * 
	 * @param pAction 唯一动作名
	 * @param pDefaultDataValue  缺省数据
	 */
	public EventSource(String pAction, Object pDefaultDataValue){
		Map<String, Object> datas = new HashMap<String, Object>();
		datas.put(EventSource.DEFAULT_KEY, pDefaultDataValue);
		this.init(pAction, datas);
	}
	
	/**
	 * 
	 * @param pAction 唯一动作名
	 * @param pDatas   数据
	 */
	public EventSource(String pAction, Map<String, Object> pDatas){
		this.init(pAction, pDatas);
	}
	
	
	// ===========================================================
	// 事件源处理
	// ===========================================================
	/**
	 * 初始化
	 * @param pAction
	 * @param pDatas
	 */
	private void init(String pAction, Map<String, Object> pDatas){
		this.mAction = pAction;
		if (pDatas != null) {
			for (String key : pDatas.keySet()) {
				if (pDatas.get(key) == null) {
					pDatas.remove(key);
				}
			}
			this.mDatas.clear();
			this.mDatas.putAll(pDatas);
		}
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

	/**
	 * 获取缺省值
	 * @return
	 */
	public Object getDefaultObject(){
		return this.getObjectExtra(EventSource.DEFAULT_KEY);
	}
	

	@Override
	public String getEventName() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String getEventMessage() {
		// TODO Auto-generated method stub
		return "";
	}
	
}
