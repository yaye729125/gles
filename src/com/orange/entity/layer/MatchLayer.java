package com.orange.entity.layer;

import com.orange.entity.scene.Scene;

/**
 * 匹配全屏的Layer
 * @author OrangeGame <OGEngine@orangegame.cn>
 *
 */
public class MatchLayer extends Layer{

	public MatchLayer(Scene pGameScene) {
		super(pGameScene.getCameraWidth(), pGameScene.getCameraHeight(), pGameScene);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取的屏幕的宽度
	 * @return
	 */
	@Override
	public float getWidth() {
		// TODO Auto-generated method stub
		return super.getWidth();
	}

	/**
	 * 获得屏幕的高度
	 * @return
	 */
	@Override
	public float getHeight() {
		// TODO Auto-generated method stub
		return super.getHeight();
	}
	
	
}
