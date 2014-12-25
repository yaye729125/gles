package com.orange.entity.sprite;

import com.orange.util.modifier.IModifier.DeepCopyNotSupportedException;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IAnimationData {
	// ===========================================================
	// Constants
	// ===========================================================

	public static final int LOOP_CONTINUOUS = -1;

	// ===========================================================
	// Methods
	// ===========================================================
	
	public int[] getFrames();
	public long[] getFrameDurations();
	public int getLoopCount();
	public int getFrameCount();
	public int getFirstFrameIndex();
	/**
	 * @return in milliseconds.
	 */
	public long getAnimationDuration();

	public int calculateCurrentFrameIndex(final long pAnimationProgress);

	public void set(final long pFrameDurationEach, final int pFrameCount);
	public void set(final long pFrameDurationEach, final int pFrameCount, final boolean pLoop);
	public void set(final long pFrameDurationEach, final int pFrameCount, final int pLoopCount);

	public void set(final long[] pFrameDurations);
	public void set(final long[] pFrameDurations, final boolean pLoop);
	public void set(final long[] pFrameDurations, final int pLoopCount);

	public void set(final long[] pFrameDurations, final int pFirstFrameIndex, final int pLastFrameIndex);
	public void set(final long[] pFrameDurations, final int pFirstFrameIndex, final int pLastFrameIndex, final boolean pLoop);
	/**
	 * @param pFrameDurations must have the same length as pFirstFrameIndex to pLastFrameIndex.
	 * @param pFirstFrameIndex
	 * @param pLastFrameIndex
	 * @param pLoopCount
	 */
	public void set(final long[] pFrameDurations, final int pFirstFrameIndex, final int pLastFrameIndex, final int pLoopCount);

	/**
	 * Animate specifics frames.
	 * 
	 * @param pFrameDurations must have the same length as pFrames.
	 * @param pFrames indices of the frames to animate.
	 */
	public void set(final long[] pFrameDurations, final int[] pFrames);
	/**
	 * Animate specifics frames.
	 * 
	 * @param pFrameDurations must have the same length as pFrames.
	 * @param pFrames indices of the frames to animate.
	 * @param pLoop
	 */
	public void set(final long[] pFrameDurations, final int[] pFrames, final boolean pLoop);
	/**
	 * Animate specifics frames.
	 * 
	 * @param pFrameDurations must have the same length as pFrames.
	 * @param pFrames indices of the frames to animate.
	 * @param pLoopCount
	 */
	public void set(final long[] pFrameDurations, final int[] pFrames, final int pLoopCount);

	public void set(final IAnimationData pAnimationData);

	public IAnimationData deepCopy() throws DeepCopyNotSupportedException;
}
