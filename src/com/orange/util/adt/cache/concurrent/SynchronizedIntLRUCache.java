package com.orange.util.adt.cache.concurrent;

import com.orange.util.adt.cache.IntLRUCache;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class SynchronizedIntLRUCache<V> extends IntLRUCache<V> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public SynchronizedIntLRUCache(final int pCapacity) {
		super(pCapacity);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public synchronized int getSize() {
		return super.getSize();
	}

	@Override
	public synchronized boolean isEmpty() {
		return super.isEmpty();
	}

	@Override
	public synchronized V put(final int pKey, final V pValue) {
		return super.put(pKey, pValue);
	}

	@Override
	public synchronized V get(final int pKey) {
		return super.get(pKey);
	}

	@Override
	public synchronized void clear() {
		super.clear();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
