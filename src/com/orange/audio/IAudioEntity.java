package com.orange.audio;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IAudioEntity {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void play();
	public void pause();
	public void resume();
	public void stop();

	public float getVolume();
	public void setVolume(final float pVolume);

	public float getLeftVolume();
	public float getRightVolume();
	public void setVolume(final float pLeftVolume, final float pRightVolume);

	public void onMasterVolumeChanged(final float pMasterVolume);

	public void setLooping(final boolean pLooping);

	public void release();
}
