package com.orange.util;

import com.orange.util.exception.AndEngineRuntimeException;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IDisposable {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public boolean isDisposed();
	public void dispose() throws AlreadyDisposedException;

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	public class AlreadyDisposedException extends AndEngineRuntimeException {
		// ===========================================================
		// Constants
		// ===========================================================

		private static final long serialVersionUID = 5796912098160771249L;

		// ===========================================================
		// Fields
		// ===========================================================

		// ===========================================================
		// Constructors
		// ===========================================================

		public AlreadyDisposedException() {
			
		}

		public AlreadyDisposedException(String pMessage) {
			super(pMessage);
		}

		public AlreadyDisposedException(Throwable pThrowable) {
			super(pThrowable);
		}
		
		public AlreadyDisposedException(String pMessage, Throwable pThrowable) {
			super(pMessage, pThrowable);
		}

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
