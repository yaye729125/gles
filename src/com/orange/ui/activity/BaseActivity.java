package com.orange.ui.activity;

import com.orange.util.ActivityUtils;
import com.orange.util.call.AsyncCallable;
import com.orange.util.call.Callable;
import com.orange.util.call.Callback;
import com.orange.util.progress.ProgressCallable;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Looper;
import android.widget.Toast;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public abstract class BaseActivity extends Activity {
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

	public void toastOnUIThread(final CharSequence pText) {
		this.toastOnUIThread(pText, Toast.LENGTH_LONG);
	}

	public void toastOnUIThread(final CharSequence pText, final int pDuration) {
		if(Looper.getMainLooper().getThread() == Thread.currentThread()) {
			Toast.makeText(BaseActivity.this, pText, pDuration).show();
		} else {
			this.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(BaseActivity.this, pText, pDuration).show();
				}
			});
		}
	}

	/**
	 * Performs a task in the background, showing a {@link ProgressDialog},
	 * while the {@link Callable} is being processed.
	 * 
	 * @param <T>
	 * @param pTitleResourceID
	 * @param pMessageResourceID
	 * @param pErrorMessageResourceID
	 * @param pCallable
	 * @param pCallback
	 */
	protected <T> void doAsync(final int pTitleResourceID, final int pMessageResourceID, final Callable<T> pCallable, final Callback<T> pCallback) {
		this.doAsync(pTitleResourceID, pMessageResourceID, pCallable, pCallback, null);
	}

	/**
	 * Performs a task in the background, showing a indeterminate {@link ProgressDialog},
	 * while the {@link Callable} is being processed.
	 * 
	 * @param <T>
	 * @param pTitleResourceID
	 * @param pMessageResourceID
	 * @param pErrorMessageResourceID
	 * @param pCallable
	 * @param pCallback
	 * @param pExceptionCallback
	 */
	protected <T> void doAsync(final int pTitleResourceID, final int pMessageResourceID, final Callable<T> pCallable, final Callback<T> pCallback, final Callback<Exception> pExceptionCallback) {
		ActivityUtils.doAsync(this, pTitleResourceID, pMessageResourceID, pCallable, pCallback, pExceptionCallback);
	}

	/**
	 * Performs a task in the background, showing a {@link ProgressDialog} with an ProgressBar,
	 * while the {@link AsyncCallable} is being processed.
	 * 
	 * @param <T>
	 * @param pTitleResourceID
	 * @param pMessageResourceID
	 * @param pErrorMessageResourceID
	 * @param pAsyncCallable
	 * @param pCallback
	 */
	protected <T> void doProgressAsync(final int pTitleResourceID, final int pIconResourceID, final ProgressCallable<T> pCallable, final Callback<T> pCallback) {
		this.doProgressAsync(pTitleResourceID, pIconResourceID, pCallable, pCallback, null);
	}

	/**
	 * Performs a task in the background, showing a {@link ProgressDialog} with a ProgressBar,
	 * while the {@link AsyncCallable} is being processed.
	 * 
	 * @param <T>
	 * @param pTitleResourceID
	 * @param pMessageResourceID
	 * @param pErrorMessageResourceID
	 * @param pAsyncCallable
	 * @param pCallback
	 * @param pExceptionCallback
	 */
	protected <T> void doProgressAsync(final int pTitleResourceID, final int pIconResourceID, final ProgressCallable<T> pCallable, final Callback<T> pCallback, final Callback<Exception> pExceptionCallback) {
		ActivityUtils.doProgressAsync(this, pTitleResourceID, pIconResourceID, pCallable, pCallback, pExceptionCallback);
	}

	/**
	 * Performs a task in the background, showing an indeterminate {@link ProgressDialog},
	 * while the {@link AsyncCallable} is being processed.
	 * 
	 * @param <T>
	 * @param pTitleResourceID
	 * @param pMessageResourceID
	 * @param pErrorMessageResourceID
	 * @param pAsyncCallable
	 * @param pCallback
	 * @param pExceptionCallback
	 */
	protected <T> void doAsync(final int pTitleResourceID, final int pMessageResourceID, final AsyncCallable<T> pAsyncCallable, final Callback<T> pCallback, final Callback<Exception> pExceptionCallback) {
		ActivityUtils.doAsync(this, pTitleResourceID, pMessageResourceID, pAsyncCallable, pCallback, pExceptionCallback);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
