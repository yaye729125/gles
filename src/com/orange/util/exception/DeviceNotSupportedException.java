package com.orange.util.exception;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class DeviceNotSupportedException extends AndEngineException {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = 2640523490821876076L;

	// ===========================================================
	// Fields
	// ===========================================================

	private final DeviceNotSupportedCause mDeviceNotSupportedCause;

	// ===========================================================
	// Constructors
	// ===========================================================

	public DeviceNotSupportedException(final DeviceNotSupportedCause pDeviceNotSupportedCause) {
		super();

		this.mDeviceNotSupportedCause = pDeviceNotSupportedCause;
	}

	public DeviceNotSupportedException(final DeviceNotSupportedCause pDeviceNotSupportedCause, final Throwable pThrowable) {
		super(pThrowable);

		this.mDeviceNotSupportedCause = pDeviceNotSupportedCause;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public DeviceNotSupportedCause getDeviceNotSupportedCause() {
		return this.mDeviceNotSupportedCause;
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

	public static enum DeviceNotSupportedCause {
		// ===========================================================
		// Elements
		// ===========================================================

		CODEPATH_INCOMPLETE,
		EGLCONFIG_NOT_FOUND;

		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Fields
		// ===========================================================

		// ===========================================================
		// Constructors
		// ===========================================================

		// ===========================================================
		// Getter & Setter
		// ===========================================================

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
}
