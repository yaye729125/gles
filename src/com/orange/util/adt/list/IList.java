package com.orange.util.adt.list;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IList<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public boolean isEmpty();
	public T get(final int pIndex) throws IndexOutOfBoundsException;
	public void set(final int pIndex, final T pItem) throws IndexOutOfBoundsException;
	public int indexOf(final T pItem);
	public void add(final T pItem);
	public void add(final int pIndex, final T pItem) throws IndexOutOfBoundsException;
	public boolean remove(final T pItem);
	public T removeFirst();
	public T removeLast();
	public T remove(final int pIndex) throws IndexOutOfBoundsException;
	public int size();
	public void clear();
}
