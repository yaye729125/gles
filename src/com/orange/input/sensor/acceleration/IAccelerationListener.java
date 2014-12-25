package com.orange.input.sensor.acceleration;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IAccelerationListener {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void onAccelerationAccuracyChanged(final AccelerationData pAccelerationData);
	public void onAccelerationChanged(final AccelerationData pAccelerationData);
}
