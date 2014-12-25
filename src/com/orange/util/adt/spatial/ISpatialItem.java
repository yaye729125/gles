package com.orange.util.adt.spatial;

import com.orange.util.adt.bounds.IBounds;


/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface ISpatialItem<B extends IBounds> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public B getBounds();
}
