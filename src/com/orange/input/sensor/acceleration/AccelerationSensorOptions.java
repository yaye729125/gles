package com.orange.input.sensor.acceleration;

import com.orange.input.sensor.SensorDelay;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class AccelerationSensorOptions {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	final SensorDelay mSensorDelay;

	// ===========================================================
	// Constructors
	// ===========================================================

	public AccelerationSensorOptions(final SensorDelay pSensorDelay) {
		this.mSensorDelay = pSensorDelay;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public SensorDelay getSensorDelay() {
		return this.mSensorDelay;
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
