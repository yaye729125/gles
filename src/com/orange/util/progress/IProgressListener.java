package com.orange.util.progress;


/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IProgressListener {
	// ===========================================================
	// Constants
	// ===========================================================

	public static final int PROGRESS_MIN = 0;
	public static final int PROGRESS_MAX = 100;

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * @param pProgress between 0 and 100.
	 */
	public void onProgressChanged(final int pProgress);
}