package com.orange.util.progress;


/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface ProgressCallable<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * Computes a result, or throws an exception if unable to do so.
	 * @param pProgressListener
	 * @return computed result
	 * @throws Exception if unable to compute a result
	 */
	public T call(final IProgressListener pProgressListener) throws Exception;
}