package com.orange.engine.handler.collision;

import com.orange.entity.shape.IShape;

/**
 * 碰撞处理Callback
 * (c) OrangeGame 2012 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface ICollisionCallback {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * 碰撞
	 * @param pCheckShape
	 * @param pTargetShape
	 * @return <code>true</code> to proceed, <code>false</code> to stop further collosion-checks.
	 */
	public boolean onCollision(final IShape pCheckShape, final IShape pTargetShape);
}
