package com.orange.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

public class Launcher {
	
	private static final String TAG = Launcher.class.getSimpleName();

	private final Activity mActivity;

	public Launcher(Activity pActivity) {
		this.mActivity = pActivity;
	}

	public Activity getActivity() {
		return mActivity;
	}

	///////////////////////////////////////
	
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onCreate");
	}

	public void onPause() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onPause");
	}

	public void onResume() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onResume");
	}

	public void onStart() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onStart");
	}

	public void onStop() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onStop");
	}

	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onDestroy");
	}

	public void onWindowFocusChanged(final boolean pHasWindowFocus) {
		Log.d(TAG, "onWindowFocusChanged");
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d(TAG, "onKeyDown");
		return false;
	}

	public boolean onKeyUp(int keyCode, KeyEvent event) {
		Log.d(TAG, "onKeyUp");
		return false;
	}
	
	public void finish() {
		Log.d(TAG, "finish");
		this.getActivity().finish();
	}
}
