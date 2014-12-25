package com.orange.res;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;

import com.orange.audio.sound.Sound;
import com.orange.audio.sound.SoundFactory;
import com.orange.audio.sound.SoundManager;

public class SoundRes {


	// ===========================================================
	// 变量
	// ===========================================================
	private final Context mContext;
	private final SoundManager mSoundManager;
	
	private final Map<String, Sound> mSoundMap = new HashMap<String, Sound>();

	// ===========================================================
	// 构造
	// ===========================================================

	public SoundRes(Context pContext, SoundManager pSoundManager) {
		// SoundFactory.setAssetBasePath("mfx/");
		this.mContext = pContext;
		this.mSoundManager = pSoundManager;
	}

	public void loadSoundFromAssetsFile(String pKey, String pSoundFileName) {
		try {
			Sound sound = SoundFactory.createSoundFromAsset(this.mSoundManager, this.mContext, pSoundFileName);
			this.mSoundMap.put(pKey, sound);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void playSoundFromMap(String pKey) {
		Sound sound = this.mSoundMap.get(pKey);
		if (sound != null) {
			sound.play();
		}
	}

	public Sound getSoundFromMap(String pKey) {
		return this.mSoundMap.get(pKey);
	}

	public void setMasterVolume(float pMasterVolume) {
		this.mSoundManager.setMasterVolume(pMasterVolume);
	}
	
	public float getMasterVolume(){
		return this.mSoundManager.getMasterVolume();
	}


	// ===========================================================
	// 静态
	// ===========================================================
	
	public static void loadSoundFromAssets(String pKey, String pSoundFileName) {
		ResManager.getInstance().getSoundRes().loadSoundFromAssetsFile(pKey, pSoundFileName);
	}
	
	public static void loadSoundFromAssets(String pKey) {
		ResManager.getInstance().getSoundRes().loadSoundFromAssetsFile(pKey, pKey);
	}

	public static void playSound(String pKey) {
		ResManager.getInstance().getSoundRes().playSoundFromMap(pKey);
	}

	public static Sound getSound(String pKey) {
		return ResManager.getInstance().getSoundRes().getSoundFromMap(pKey);
	}
	
	public static void setVolume(float pMasterVolume) {
		ResManager.getInstance().getSoundRes().setMasterVolume(pMasterVolume);
	}
	
	public static float getVolume(){
		return ResManager.getInstance().getSoundRes().getMasterVolume();
	}

	public static void onSound(float pMasterVolume) {
		ResManager.getInstance().getSoundRes().setMasterVolume(pMasterVolume);
	}

	public static void offSound() {
		ResManager.getInstance().getSoundRes().setMasterVolume(0.0f);
	}


}
