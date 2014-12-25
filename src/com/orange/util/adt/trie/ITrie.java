package com.orange.util.adt.trie;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface ITrie {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void add(final CharSequence pCharSequence);
	public void add(final CharSequence pCharSequence, final int pStart, final int pEnd);
	public boolean contains(final CharSequence pCharSequence);
	public boolean contains(final CharSequence pCharSequence, final int pStart, final int pEnd);
	/* TODO public void clear(); */
	/* TODO public boolean remove(final CharSequence pCharSequence); */
	/* TODO public boolean remove(final CharSequence pCharSequence, final int pStart, final int pEnd); */
}
