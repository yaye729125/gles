package com.orange.entity.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.orange.engine.camera.Camera;
import com.orange.entity.Entity;
import com.orange.entity.util.ScreenGrabber.IScreenGrabberCallback;
import com.orange.opengl.util.GLState;
import com.orange.util.StreamUtils;
import com.orange.util.debug.Debug;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class ScreenCapture extends Entity implements IScreenGrabberCallback {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private String mFilePath;

	private final ScreenGrabber mScreenGrabber = new ScreenGrabber();

	private IScreenCaptureCallback mScreenCaptureCallback;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void onManagedDraw(final GLState pGLState, final Camera pCamera) {
		this.mScreenGrabber.onManagedDraw(pGLState, pCamera);
	}

	@Override
	protected void onManagedUpdate(final float pSecondsElapsed) {
		/* Nothing */
	}

	@Override
	public void reset() {
		/* Nothing */
	}

	@Override
	public void onScreenGrabbed(final Bitmap pBitmap) {
		try {
			ScreenCapture.saveCapture(pBitmap, this.mFilePath);
			this.mScreenCaptureCallback.onScreenCaptured(this.mFilePath);
		} catch (final FileNotFoundException e) {
			this.mScreenCaptureCallback.onScreenCaptureFailed(this.mFilePath, e);
		}
	}

	@Override
	public void onScreenGrabFailed(final Exception pException) {
		this.mScreenCaptureCallback.onScreenCaptureFailed(this.mFilePath, pException);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void capture(final int pCaptureWidth, final int pCaptureHeight, final String pFilePath, final IScreenCaptureCallback pScreenCaptureCallback) {
		this.capture(0, 0, pCaptureWidth, pCaptureHeight, pFilePath, pScreenCaptureCallback);
	}

	public void capture(final int pCaptureX, final int pCaptureY, final int pCaptureWidth, final int pCaptureHeight, final String pFilePath, final IScreenCaptureCallback pScreencaptureCallback) {
		this.mFilePath = pFilePath;
		this.mScreenCaptureCallback = pScreencaptureCallback;
		this.mScreenGrabber.grab(pCaptureX, pCaptureY, pCaptureWidth, pCaptureHeight, this);
	}

	private static void saveCapture(final Bitmap pBitmap, final String pFilePath) throws FileNotFoundException {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(pFilePath);
			pBitmap.compress(CompressFormat.PNG, 100, out);
		} catch (final FileNotFoundException e) {
			StreamUtils.flushCloseStream(out);
			Debug.e("Error saving file to: " + pFilePath, e);
			throw e;
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	public static interface IScreenCaptureCallback {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================

		public void onScreenCaptured(final String pFilePath);
		public void onScreenCaptureFailed(final String pFilePath, final Exception pException);
	}
}
