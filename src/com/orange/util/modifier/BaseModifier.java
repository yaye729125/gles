package com.orange.util.modifier;

import com.orange.util.adt.list.SmartList;


/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 * @param <T>
 */
public abstract class BaseModifier<T> implements IModifier<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	protected boolean mFinished;
	private boolean mAutoUnregisterWhenFinished = true;
	private final SmartList<IModifierListener<T>> mModifierListeners = new SmartList<IModifierListener<T>>(2);

	// ===========================================================
	// Constructors
	// ===========================================================

	public BaseModifier() {

	}

	public BaseModifier(final IModifierListener<T> pModifierListener) {
		this.addModifierListener(pModifierListener);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public boolean isFinished() {
		return this.mFinished;
	}

	@Override
	public final boolean isAutoUnregisterWhenFinished() {
		return this.mAutoUnregisterWhenFinished;
	}

	/**是否当修改器结束时自动反注册 默认true*/
	@Override
	public final void setAutoUnregisterWhenFinished(final boolean pAutoUnregisterWhenFinished) {
		this.mAutoUnregisterWhenFinished = pAutoUnregisterWhenFinished;
	}

	/**添加ModifierListener监听,监听Modifier开始与结束*/
	@Override
	public void addModifierListener(final IModifierListener<T> pModifierListener) {
		if(pModifierListener != null) {
			this.mModifierListeners.add(pModifierListener);
		}
	}

	/**去除ModifierListener监听,监听Modifier开始与结束*/
	@Override
	public boolean removeModifierListener(final IModifierListener<T> pModifierListener) {
		if(pModifierListener == null) {
			return false;
		} else {
			return this.mModifierListeners.remove(pModifierListener);
		}
	}

	@Override
	public abstract IModifier<T> deepCopy() throws DeepCopyNotSupportedException;

	// ===========================================================
	// Methods
	// ===========================================================

	protected void onModifierStarted(final T pItem) {
		final SmartList<IModifierListener<T>> modifierListeners = this.mModifierListeners;
		final int modifierListenerCount = modifierListeners.size();
		for(int i = modifierListenerCount - 1; i >= 0; i--) {
			modifierListeners.get(i).onModifierStarted(this, pItem);
		}
	}

	protected void onModifierFinished(final T pItem) {
		final SmartList<IModifierListener<T>> modifierListeners = this.mModifierListeners;
		final int modifierListenerCount = modifierListeners.size();
		for(int i = modifierListenerCount - 1; i >= 0; i--) {
			modifierListeners.get(i).onModifierFinished(this, pItem);
		}
	}

	protected static final <T> void assertNoNullModifier(final IModifier<T> pModifier) {
		if(pModifier == null) {
			throw new IllegalArgumentException("Illegal 'null' " + IModifier.class.getSimpleName() + " detected!");
		}
	}

	protected static final <T> void assertNoNullModifier(final IModifier<T> ... pModifiers) {
		final int modifierCount = pModifiers.length;
		for(int i = 0; i < modifierCount; i++) {
			if(pModifiers[i] == null) {
				throw new IllegalArgumentException("Illegal 'null' " + IModifier.class.getSimpleName() + " detected at position: '" + i + "'!");
			}
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
