package com.orange.res;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;

import com.orange.audio.music.Music;
import com.orange.audio.music.MusicFactory;
import com.orange.audio.music.MusicManager;

public class MusicRes {

	// ===========================================================
	// 变量
	// ===========================================================
	private final Context mContext;
	private final MusicManager mMusicManager;

	private final Map<String, Music> mMusicMap = new HashMap<String, Music>();

	// ===========================================================
	// 构造
	// ===========================================================

	public MusicRes(Context pContext, MusicManager pMusicManager) {
		// MusicFactory.setAssetBasePath("mfx/");
		this.mContext = pContext;
		this.mMusicManager = pMusicManager;
	}

	public void loadMusicFromAssetsFile(String key, String pMusicFileName) {
		try {
			Music mMusic = MusicFactory.createMusicFromAsset(this.mMusicManager, this.mContext, pMusicFileName);
			this.mMusicMap.put(key, mMusic);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void playMusicFromMap(String pKey, boolean pLooping) {
		Music mMusic = this.mMusicMap.get(pKey);
		if (mMusic != null && !mMusic.isPlaying()) {
			mMusic.setLooping(pLooping);
			mMusic.play();
		}
	}

	public void pauseMusicFromMap(String pKey) {
		Music mMusic = this.mMusicMap.get(pKey);
		if (mMusic != null && mMusic.isPlaying()) {
			mMusic.pause();
		}
	}

	public void setMasterVolume(float pMasterVolume) {
		this.mMusicManager.setMasterVolume(pMasterVolume);
	}
	
	public float getMasterVolume() {
		return this.mMusicManager.getMasterVolume();
	}
	
	// ===========================================================
	// 静态
	// ===========================================================

	public static void loadMusicFromAssets(String pKey, String musicFileName) {
		ResManager.getInstance().getMusicRes().loadMusicFromAssetsFile(pKey, musicFileName);
	}
	
	public static void loadMusicFromAssets(String pKey) {
		ResManager.getInstance().getMusicRes().loadMusicFromAssetsFile(pKey, pKey);
	}

	public static void playMusic(String pKey, boolean pLooping) {
		ResManager.getInstance().getMusicRes().playMusicFromMap(pKey, pLooping);
	}

	public static void pauseMusic(String pKey) {
		ResManager.getInstance().getMusicRes().pauseMusicFromMap(pKey);
	}
	
	public static void setVolume(float pMasterVolume) {
		ResManager.getInstance().getMusicRes().setMasterVolume(pMasterVolume);
	}
	
	public static float getVolume() {
		return ResManager.getInstance().getMusicRes().getMasterVolume();
	}

	public static void onMusic(float pMasterVolume) {
		ResManager.getInstance().getMusicRes().setMasterVolume(pMasterVolume);
	}

	public static void offMusic() {
		ResManager.getInstance().getMusicRes().setMasterVolume(0.0f);
	}


}
