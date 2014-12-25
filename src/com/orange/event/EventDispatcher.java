package com.orange.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Handler;

/**
 * 广播调度，主要用于接收发送处理广播
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 * 
 */
public class EventDispatcher {
	
	// ===========================================================
	// 单例处理部分
	// ===========================================================

	private static EventDispatcher eventDispatcher; 

	/**
	 * 获取实例
	 * @return
	 */
	public static EventDispatcher getInstance() {
		if (eventDispatcher == null) {
			eventDispatcher = new EventDispatcher();
		}
		return eventDispatcher;
	}
	
	/**
	 * 初始化
	 */
	public static void init() {
		eventDispatcher = new EventDispatcher();
	}
	
	// ===========================================================
	// 变量
	// ===========================================================
	
	private Handler mHandler = new Handler();

	/**
	 * 接收器索引列表
	 */
	private Map<String, List<EventReceiver>> mEventReceiverMap = new HashMap<String, List<EventReceiver>>();

	
	// ===========================================================
	// 广播处理相关方法
	// ===========================================================
	
	/**
	 * 发送广播
	 * 
	 * @param eventIntentSend
	 */
	private void broadcast(final EventSource pEventSource) {
		mHandler.post(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				EventDispatcher.this.handlerBroadcast(pEventSource);
			}
		});
	}
	
	/**
	 * 发送广播
	 * 
	 * @param eventIntentSend
	 */
	private void handlerBroadcast(EventSource pEventSource) {
		if (pEventSource == null) {
			return;
		}
		String action = pEventSource.getAction();
		if (action == null || action.length() <= 0) {
			// 广播动作为null或长度为0，不处理
			return;
		}
		
		if (mEventReceiverMap.get(action) == null) {
			return;
		}
		List<EventReceiver> actionEventReceivers = new ArrayList<EventReceiver>(mEventReceiverMap.get(action));
		for (EventReceiver eventReceiver : actionEventReceivers) {
			if (eventReceiver == null) {
				break;
			}
			boolean result = eventReceiver.onReceive(pEventSource);
			if (result) {
				// 返回true广播停止
				break;
			}
		}
	}
	

	/**
	 * 
	 * @param pIndex 小于0时，添加到最后
	 * @param pEventReceiver
	 */
	private void addReceiver(int pIndex, EventReceiver pEventReceiver) {
		if (pEventReceiver == null) {
			return;
		}
		String action = pEventReceiver.getAction();
		if (action == null || action.length() <= 0) {
			return;
		}
		List<EventReceiver> eventReceivers = this.mEventReceiverMap.get(action);
		if (eventReceivers == null) {
			eventReceivers = new ArrayList<EventReceiver>();
			this.mEventReceiverMap.put(action, eventReceivers);
		}
		if (!eventReceivers.contains(pEventReceiver)) {
			if (pIndex < 0) {
				eventReceivers.add(pEventReceiver);
			}else {
				eventReceivers.add(pIndex, pEventReceiver);
			}
		}
	}
	
	/**
	 * 添加接收器
	 * 
	 * @param eventIntentReceiver
	 */
	private void addReceiver(EventReceiver pEventReceiver) {
		this.addReceiver(-1, pEventReceiver);
	}

	/**
	 * 添加接收器到第一位
	 * 
	 * @param eventIntentReceiver
	 */
	private void addReceiverToFirst(EventReceiver pEventReceiver) {
		this.addReceiver(0, pEventReceiver);
	}

	/**
	 * 删除接收器
	 * 
	 * @param eventIntentReceiver
	 */
	private void removeReceiver(EventReceiver pEventReceiver) {
		if (pEventReceiver == null) {
			return;
		}
		String action = pEventReceiver.getAction();
		if (action == null || action.length() <= 0) {
			return;
		}
		List<EventReceiver> eventReceivers = this.mEventReceiverMap.get(action);
		if (eventReceivers == null) {
			eventReceivers = new ArrayList<EventReceiver>();
			this.mEventReceiverMap.put(action, eventReceivers);
		}
		if (eventReceivers.contains(pEventReceiver)) {
			eventReceivers.remove(pEventReceiver);
		}
	}

	// ===========================================================
	// 静态方法，供外部使用
	// ===========================================================
	
	
	/**
	 * 发送广播
	 * 
	 * @param eventIntentSend
	 */
	public static void sendBroadcast(EventSource pEventSource) {
		EventDispatcher.getInstance().broadcast(pEventSource);
	}

	/**
	 * 注册接收器
	 * 如不需要此接收需要调用unregisterReceiver反注册
	 * 
	 * @param eventIntentReceiver
	 */
	public static void registerReceiver(EventReceiver pEventReceiver) {
		EventDispatcher.getInstance().addReceiver(pEventReceiver);
	}

	/**
	 * 注册接收器到第一位
	 * 如不需要此接收需要调用unregisterReceiver反注册
	 * 
	 * @param eventIntentReceiver
	 */
	public static void registerReceiverToFirst(EventReceiver pEventReceiver) {
		EventDispatcher.getInstance().addReceiverToFirst(pEventReceiver);
	}

	/**
	 * 反注册接收器
	 * 
	 * @param eventIntentReceiver
	 */
	public static void unregisterReceiver(EventReceiver pEventReceiver) {
		EventDispatcher.getInstance().removeReceiver(pEventReceiver);
	}
	
	// ===========================================================
	// \静态方法，供外部使用
	// ===========================================================

}
