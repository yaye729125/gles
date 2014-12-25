package com.orange.entity;

import com.orange.util.call.ParameterCallable;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IEntityParameterCallable extends ParameterCallable<IEntity> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	@Override
	public void call(final IEntity pEntity);
}