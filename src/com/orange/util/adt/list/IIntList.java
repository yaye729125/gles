package com.orange.util.adt.list;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IIntList {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public boolean isEmpty();
	public float get(final int pIndex) throws ArrayIndexOutOfBoundsException;
	public void add(final int pItem);
	public void add(final int pIndex, final int pItem) throws ArrayIndexOutOfBoundsException;
	public float remove(final int pIndex) throws ArrayIndexOutOfBoundsException;
	public int size();
	public void clear();
	public int[] toArray();
}