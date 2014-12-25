package com.orange.input.sensor;

import android.hardware.SensorManager;


/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public enum SensorDelay {
	// ===========================================================
	// Elements
	// ===========================================================

	NORMAL(SensorManager.SENSOR_DELAY_NORMAL),
	UI(SensorManager.SENSOR_DELAY_UI),
	GAME(SensorManager.SENSOR_DELAY_GAME),
	FASTEST(SensorManager.SENSOR_DELAY_FASTEST);

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final int mDelay;

	// ===========================================================
	// Constructors
	// ===========================================================

	private SensorDelay(final int pDelay) {
		this.mDelay = pDelay;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public int getDelay() {
		return this.mDelay;
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
