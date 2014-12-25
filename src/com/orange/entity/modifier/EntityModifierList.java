package com.orange.entity.modifier;

import com.orange.entity.IEntity;
import com.orange.util.modifier.ModifierList;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class EntityModifierList extends ModifierList<IEntity> {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = 161652765736600082L;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public EntityModifierList(final IEntity pTarget) {
		super(pTarget);
	}

	public EntityModifierList(final IEntity pTarget, final int pCapacity) {
		super(pTarget, pCapacity);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
