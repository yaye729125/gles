package com.orange.input.sensor.orientation;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IOrientationListener {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void onOrientationAccuracyChanged(final OrientationData pOrientationData);
	public void onOrientationChanged(final OrientationData pOrientationData);
}
