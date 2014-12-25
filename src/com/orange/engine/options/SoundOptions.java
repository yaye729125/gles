package com.orange.engine.options;

import com.orange.audio.sound.SoundManager;

/**
 * 声音选项
 * (c) OrangeGame 2012
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class SoundOptions {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private boolean mNeedsSound;
	private int mMaxSimultaneousStreams = SoundManager.MAX_SIMULTANEOUS_STREAMS_DEFAULT;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public boolean needsSound() {
		return this.mNeedsSound;
	}

	public SoundOptions setNeedsSound(final boolean pNeedsSound) {
		this.mNeedsSound = pNeedsSound;
		return this;
	}

	public int getMaxSimultaneousStreams() {
		return this.mMaxSimultaneousStreams;
	}

	public SoundOptions setMaxSimultaneousStreams(final int pMaxSimultaneousStreams) {
		this.mMaxSimultaneousStreams = pMaxSimultaneousStreams;
		return this;
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
