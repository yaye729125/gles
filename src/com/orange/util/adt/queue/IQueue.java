package com.orange.util.adt.queue;

import com.orange.util.adt.list.IList;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IQueue<T> extends IList<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public T peek();
	public T poll();
	public void enter(final T pItem);
	public void enter(final int pIndex, final T pItem) throws IndexOutOfBoundsException;
}
