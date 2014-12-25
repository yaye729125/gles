package com.orange.engine.handler;

import com.orange.entity.IEntity;

/**
 * 实体更新handler
 * (c) OrangeGame 2012
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public abstract class BaseEntityUpdateHandler implements IUpdateHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private IEntity mEntity;

	// ===========================================================
	// Constructors
	// ===========================================================
	/**
	 * 
	 * @param pEntity 实体对象
	 */
	public BaseEntityUpdateHandler(final IEntity pEntity) {
		this.mEntity = pEntity;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public IEntity getEntity() {
		return this.mEntity;
	}

	public void setEntity(final IEntity pEntity) {
		this.mEntity = pEntity;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	/**
	 * 实体更新
	 * @param pSecondsElapsed 间隔时间
	 * @param pEntity 实体对象
	 */
	protected abstract void onUpdate(final float pSecondsElapsed, final IEntity pEntity);

	@Override
	public final void onUpdate(final float pSecondsElapsed) {
		this.onUpdate(pSecondsElapsed, this.mEntity);
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
