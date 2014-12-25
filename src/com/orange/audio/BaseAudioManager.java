package com.orange.audio;

import java.util.ArrayList;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public abstract class BaseAudioManager<T extends IAudioEntity> implements IAudioManager<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	protected final ArrayList<T> mAudioEntities = new ArrayList<T>();

	protected float mMasterVolume = 1.0f;

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
	public float getMasterVolume() {
		return this.mMasterVolume;
	}

	@Override
	public void setMasterVolume(final float pMasterVolume) {
		this.mMasterVolume = pMasterVolume;

		final ArrayList<T> audioEntities = this.mAudioEntities;
		for(int i = audioEntities.size() - 1; i >= 0; i--) {
			final T audioEntity = audioEntities.get(i);

			audioEntity.onMasterVolumeChanged(pMasterVolume);
		}
	}

	@Override
	public void add(final T pAudioEntity) {
		this.mAudioEntities.add(pAudioEntity);
	}

	@Override
	public boolean remove(final T pAudioEntity) {
		return this.mAudioEntities.remove(pAudioEntity);
	}

	@Override
	public void releaseAll() {
		final ArrayList<T> audioEntities = this.mAudioEntities;
		for(int i = audioEntities.size() - 1; i >= 0; i--) {
			final T audioEntity = audioEntities.get(i);

			audioEntity.stop();
			audioEntity.release();
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
