package com.orange.engine.handler.collision;

import java.util.ArrayList;

import com.orange.engine.handler.IUpdateHandler;
import com.orange.entity.shape.IShape;
import com.orange.util.adt.list.ListUtils;

/**
 * 碰撞处理
 * (c) OrangeGame 2012 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class CollisionHandler implements IUpdateHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final ICollisionCallback mCollisionCallback;
	private final IShape mCheckShape;
	private final ArrayList<? extends IShape> mTargetStaticEntities;

	// ===========================================================
	// Constructors
	// ===========================================================
	/**
	 * CollisionHandler构造函数
	 * @param pCollisionCallback 
	 * @param pCheckShape    检测模型
	 * @param pTargetShape   当前模型
	 * @throws IllegalArgumentException
	 */
	public CollisionHandler(final ICollisionCallback pCollisionCallback, final IShape pCheckShape, final IShape pTargetShape) throws IllegalArgumentException {
		this(pCollisionCallback, pCheckShape, ListUtils.toList(pTargetShape));
	}

	/**
	 * CollisionHandler构造函数
	 * @param pCollisionCallback
	 * @param pCheckShape            检测模型
	 * @param pTargetStaticEntities  当前模型列表
	 * @throws IllegalArgumentException
	 */
	public CollisionHandler(final ICollisionCallback pCollisionCallback, final IShape pCheckShape, final ArrayList<? extends IShape> pTargetStaticEntities) throws IllegalArgumentException {
		if (pCollisionCallback == null) {
			throw new IllegalArgumentException( "pCollisionCallback must not be null!");
		}
		if (pCheckShape == null) {
			throw new IllegalArgumentException( "pCheckShape must not be null!");
		}
		if (pTargetStaticEntities == null) {
			throw new IllegalArgumentException( "pTargetStaticEntities must not be null!");
		}

		this.mCollisionCallback = pCollisionCallback;
		this.mCheckShape = pCheckShape;
		this.mTargetStaticEntities = pTargetStaticEntities;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onUpdate(final float pSecondsElapsed) {
		final IShape checkShape = this.mCheckShape;
		final ArrayList<? extends IShape> staticEntities = this.mTargetStaticEntities;
		final int staticEntityCount = staticEntities.size();

		for(int i = 0; i < staticEntityCount; i++){
			if(checkShape.collidesWith(staticEntities.get(i))){
				final boolean proceed = this.mCollisionCallback.onCollision(checkShape, staticEntities.get(i));
				if(!proceed) {
					return;
				}
			}
		}
	}

	@Override
	public void reset() {

	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
