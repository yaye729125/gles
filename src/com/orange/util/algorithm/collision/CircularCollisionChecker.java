package com.orange.util.algorithm.collision;

import com.orange.entity.IEntity;
import com.orange.util.math.MathUtils;

/**
 * 圆形碰撞
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 * 
 */
public class CircularCollisionChecker {

	/**
	 * 计算某个精灵是否在指定圆形范围内
	 * 
	 * @param pX
	 *            指定坐标点的中心X
	 * @param pY
	 *            指定坐标点的中心X
	 * @param R
	 *            半径
	 * @param sprite
	 *            精灵
	 * @return
	 */
	public static boolean checkCollision(int pX, int pY, float R, IEntity pEntity) {
		if (MathUtils.distance(pX, pY, pEntity.getX(), pEntity.getY()) <= R) {
			return true;
		}
		if (MathUtils.distance(pX, pY, pEntity.getX() + pEntity.getWidth(), pEntity.getY()) <= R) {
			return true;
		}
		if (MathUtils.distance(pX, pY, pEntity.getX(), pEntity.getY() + pEntity.getHeight()) <= R) {
			return true;
		}
		if (MathUtils.distance(pX, pY, pEntity.getX() + pEntity.getWidth(), pEntity.getY() + pEntity.getHeight()) <= R) {
			return true;
		}
		return false;
	}

}
