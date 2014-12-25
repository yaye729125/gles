package com.orange.util.modifier;

import com.orange.engine.handler.IUpdateHandler;
import com.orange.util.adt.list.SmartList;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class ModifierList<T> extends SmartList<IModifier<T>> implements IUpdateHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = 1610345592534873475L;

	// ===========================================================
	// Fields
	// ===========================================================

	private final T mTarget;

	// ===========================================================
	// Constructors
	// ===========================================================

	public ModifierList(final T pTarget) {
		this.mTarget = pTarget;
	}

	public ModifierList(final T pTarget, final int pCapacity){
		super(pCapacity);
		this.mTarget = pTarget;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public T getTarget() {
		return this.mTarget;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public boolean add(final IModifier<T> pModifier) {
		if(pModifier == null) {
			throw new IllegalArgumentException("Supplied " + IModifier.class.getSimpleName() + " must not be null.");
		} else {
			return super.add(pModifier);
		}
	}

	@Override
	public void onUpdate(final float pSecondsElapsed) {
		final int modifierCount = this.size();
		if(modifierCount > 0) {
			for(int i = modifierCount - 1; i >= 0; i--) {
				final IModifier<T> modifier = this.get(i);
				if (modifier != null) {
					modifier.onUpdate(pSecondsElapsed, this.mTarget);
					if(modifier.isFinished() && modifier.isAutoUnregisterWhenFinished()) {
						this.remove(modifier);
					}
				}
			}
		}
	}

	@Override
	public void reset() {
		for(int i = this.size() - 1; i >= 0; i--) {
			final IModifier<T> modifier = this.get(i);
			if (modifier != null) {
				modifier.reset();
			}
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
