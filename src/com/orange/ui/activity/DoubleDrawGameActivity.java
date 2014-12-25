package com.orange.ui.activity;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.orange.engine.camera.Camera;
import com.orange.engine.handler.IDrawHandler;
import com.orange.opengl.util.GLState;
import com.orange.util.debug.Debug;

public abstract class DoubleDrawGameActivity extends GameActivity {
	
	public final static String TAG = DoubleDrawGameActivity.class.getSimpleName();

	private List<IOnResumeGameListener> mOnResumeGameListeners = new ArrayList<DoubleDrawGameActivity.IOnResumeGameListener>();
	
	@Override
	protected void onPrepareLoadComplete() {
		// TODO Auto-generated method stub
		Debug.d("onAlreadyLoadComplete");
		super.onPrepareLoadComplete();
		this.doPauseGame();
	}

	@Override
	public synchronized void doResumeGame() {
		// TODO Auto-generated method stub
		if (!this.getEngine().containsDrawHandler(this.mDrawHandler)) {
			this.getEngine().registerDrawHandler(this.mDrawHandler);
		}
		super.doResumeGame();
	}

	private IDrawHandler mDrawHandler = new IDrawHandler() {

		@Override
		public void onDraw(GLState pGLState, Camera pCamera) {
			// TODO Auto-generated method stub
			getEngine().unregisterDrawHandler(this);
			for (IOnResumeGameListener onResumeGameListener : mOnResumeGameListeners) {
				if (onResumeGameListener != null) {
					onResumeGameListener.onResumeComplete();
				}
			}
		}
	};
	
	public void registerResumeGameListener(IOnResumeGameListener pOnResumeGameListener){
		if (!this.mOnResumeGameListeners.contains(pOnResumeGameListener)) {
			this.mOnResumeGameListeners.add(pOnResumeGameListener);
		}
	}
	
	public void unregisterResumeGameListener(IOnResumeGameListener pOnResumeGameListener){
		this.mOnResumeGameListeners.remove(pOnResumeGameListener);
	}
	
	public void clearResumeGameListener(){
		this.mOnResumeGameListeners.clear();
	}
	
	public interface IOnResumeGameListener{
		
		public void onResumeComplete();
		
	}

}
