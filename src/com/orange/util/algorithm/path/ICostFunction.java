package com.orange.util.algorithm.path;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface ICostFunction<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	public float getCost(final IPathFinderMap<T> pPathFinderMap, final int pFromX, final int pFromY, final int pToX, final int pToY, final T pEntity);
}
