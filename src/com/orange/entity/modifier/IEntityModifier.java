package com.orange.entity.modifier;

import com.orange.entity.IEntity;
import com.orange.util.IMatcher;
import com.orange.util.modifier.IModifier;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IEntityModifier extends IModifier<IEntity> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	@Override
	public IEntityModifier deepCopy() throws DeepCopyNotSupportedException;

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	public static interface IEntityModifierListener extends IModifierListener<IEntity>{
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================
	}
	
	public interface IEntityModifierMatcher extends IMatcher<IModifier<IEntity>> {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================
	}
}
