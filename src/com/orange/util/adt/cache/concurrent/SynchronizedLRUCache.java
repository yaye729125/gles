package com.orange.util.adt.cache.concurrent;

import com.orange.util.adt.cache.LRUCache;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class SynchronizedLRUCache<K, V> extends LRUCache<K, V> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public SynchronizedLRUCache(final int pCapacity) {
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
	public synchronized V put(final K pKey, final V pValue) {
		return super.put(pKey, pValue);
	}

	@Override
	public synchronized V get(final K pKey) {
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
