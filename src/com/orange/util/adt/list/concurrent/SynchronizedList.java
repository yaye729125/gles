package com.orange.util.adt.list.concurrent;

import com.orange.util.adt.list.IList;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class SynchronizedList<T> implements IList<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	protected final IList<T> mList;

	// ===========================================================
	// Constructors
	// ===========================================================

	public SynchronizedList(final IList<T> mList) {
		this.mList = mList;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public synchronized boolean isEmpty() {
		return this.mList.isEmpty();
	}

	@Override
	public synchronized T get(final int pIndex) throws IndexOutOfBoundsException {
		return this.mList.get(pIndex);
	}

	@Override
	public synchronized void set(final int pIndex, final T pItem) throws IndexOutOfBoundsException {
		this.mList.set(pIndex, pItem);
	}

	@Override
	public synchronized int indexOf(final T pItem) {
		return this.mList.indexOf(pItem);
	}

	@Override
	public synchronized void add(final T pItem) {
		this.mList.add(pItem);
	}

	@Override
	public synchronized void add(final int pIndex, final T pItem) throws IndexOutOfBoundsException {
		this.mList.add(pIndex, pItem);
	}

	@Override
	public synchronized T removeFirst() {
		return this.mList.removeFirst();
	}

	@Override
	public synchronized T removeLast() {
		return this.mList.removeLast();
	}

	@Override
	public synchronized boolean remove(final T pItem) {
		return this.mList.remove(pItem);
	}

	@Override
	public synchronized T remove(final int pIndex) throws IndexOutOfBoundsException {
		return this.mList.remove(pIndex);
	}

	@Override
	public synchronized int size() {
		return this.mList.size();
	}

	@Override
	public synchronized void clear() {
		this.mList.clear();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
