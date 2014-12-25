package com.orange.util.adt.map;

import android.util.SparseArray;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 * @param <T>
 */
public class Library<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	protected final SparseArray<T> mItems;

	// ===========================================================
	// Constructors
	// ===========================================================

	public Library() {
		this.mItems = new SparseArray<T>();
	}

	public Library(final int pInitialCapacity) {
		this.mItems = new SparseArray<T>(pInitialCapacity);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public T get(final int pID) {
		return this.mItems.get(pID);
	}

	public void put(final int pID, final T pItem) {
		final T item = this.mItems.get(pID);
		if(item == null) {
			this.mItems.put(pID, pItem);
		} else {
			throw new IllegalArgumentException("ID: '" + pID + "' is already associated with item: '" + item.toString() + "'.");
		}
	}

	public void remove(final int pID) {
		this.mItems.remove(pID);
	}

	public void clear() {
		this.mItems.clear();
	}

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
