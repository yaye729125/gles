package com.orange.input.sensor.orientation;

import com.orange.input.sensor.SensorDelay;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class OrientationSensorOptions {
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

	public OrientationSensorOptions(final SensorDelay pSensorDelay) {
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
