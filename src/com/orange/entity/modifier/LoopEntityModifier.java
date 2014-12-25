package com.orange.entity.modifier;

import com.orange.entity.IEntity;
import com.orange.util.modifier.LoopModifier;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class LoopEntityModifier extends LoopModifier<IEntity> implements IEntityModifier {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public LoopEntityModifier(final IEntityModifier pEntityModifier) {
		super(pEntityModifier);
	}

	public LoopEntityModifier(final IEntityModifier pEntityModifier, final int pLoopCount) {
		super(pEntityModifier, pLoopCount);
	}

	public LoopEntityModifier(final IEntityModifier pEntityModifier, final int pLoopCount, final ILoopEntityModifierListener pLoopModifierListener) {
		super(pEntityModifier, pLoopCount, pLoopModifierListener);
	}

	public LoopEntityModifier(final IEntityModifier pEntityModifier, final int pLoopCount, final IEntityModifierListener pEntityModifierListener) {
		super(pEntityModifier, pLoopCount, pEntityModifierListener);
	}

	public LoopEntityModifier(final IEntityModifierListener pEntityModifierListener, final int pLoopCount, final ILoopEntityModifierListener pLoopModifierListener, final IEntityModifier pEntityModifier) {
		super(pEntityModifier, pLoopCount, pLoopModifierListener, pEntityModifierListener);
	}

	protected LoopEntityModifier(final LoopEntityModifier pLoopEntityModifier) throws DeepCopyNotSupportedException {
		super(pLoopEntityModifier);
	}

	@Override
	public LoopEntityModifier deepCopy() throws DeepCopyNotSupportedException {
		return new LoopEntityModifier(this);
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

	public interface ILoopEntityModifierListener extends ILoopModifierListener<IEntity> {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================
	}
}
