package com.orange.res;

import com.orange.engine.Engine;

import android.content.Context;

public class ResManager {

	// ===========================================================
	// 单例处理
	// ===========================================================

	private static ResManager mResManager = null;

	public static ResManager getInstance() {
		return mResManager;
	}
	
	public static ResManager init(Context pContext, Engine pEngine) {
		if (mResManager == null) {
			mResManager = new ResManager(pContext, pEngine);
		}
		return mResManager;
	}

	// ===========================================================
	// 变量
	// ===========================================================

	private MusicRes mMusicRes;
	private SoundRes mSoundRes;
	private RegionRes mRegionRes;
	private FontRes mFontRes;

	// ===========================================================
	// 构造
	// ===========================================================
	public ResManager(Context pContext, Engine pEngine) {
		this.mMusicRes = new MusicRes(pContext, pEngine.getMusicManager());
		this.mSoundRes = new SoundRes(pContext, pEngine.getSoundManager());
		this.mRegionRes = new RegionRes(pContext, pEngine.getTextureManager());
		this.mFontRes = new FontRes(pContext, pEngine.getFontManager(), pEngine.getTextureManager());
	}

	public MusicRes getMusicRes() {
		return mMusicRes;
	}

	public SoundRes getSoundRes() {
		return mSoundRes;
	}

	public RegionRes getRegionRes() {
		return mRegionRes;
	}

	public FontRes getFontRes() {
		return mFontRes;
	}

}
