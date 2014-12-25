package com.orange.entity;

import com.orange.util.IMatcher;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IEntityMatcher extends IMatcher<IEntity> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	@Override
	public boolean matches(final IEntity pEntity);
}