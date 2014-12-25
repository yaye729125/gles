package com.orange.opengl.view;

import com.orange.engine.Engine;

import android.R.bool;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * (c) OrangeGame 2012
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class RenderSurfaceView extends GLSurfaceView {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private EngineRenderer mEngineRenderer;
	private ConfigChooser mConfigChooser;

	// ===========================================================
	// Constructors
	// ===========================================================

	public RenderSurfaceView(final Context pContext) {
		super(pContext);

		this.setEGLContextClientVersion(2);
	}

	public RenderSurfaceView(final Context pContext, final AttributeSet pAttrs) {
		super(pContext, pAttrs);

		this.setEGLContextClientVersion(2);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public ConfigChooser getConfigChooser() throws IllegalStateException {
		if(this.mConfigChooser == null) {
			throw new IllegalStateException(ConfigChooser.class.getSimpleName() + " not yet set.");
		}
		return this.mConfigChooser;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/**
	 * @see android.view.View#measure(int, int)
	 */
	@Override
	protected void onMeasure(final int pWidthMeasureSpec, final int pHeightMeasureSpec) {
		if(this.isInEditMode()) {
			super.onMeasure(pWidthMeasureSpec, pHeightMeasureSpec);
			return;
		}
		this.mEngineRenderer.mEngine.getEngineOptions().getResolutionPolicy().onMeasure(this, pWidthMeasureSpec, pHeightMeasureSpec);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void setMeasuredDimensionProxy(final int pMeasuredWidth, final int pMeasuredHeight) {
		this.setMeasuredDimension(pMeasuredWidth, pMeasuredHeight);
	}

	public void setRenderer(final Engine pEngine, final IRendererListener pRendererListener) {
		if(this.mConfigChooser == null) {
			final boolean multiSampling = pEngine.getEngineOptions().getRenderOptions().isMultiSampling();
			this.mConfigChooser = new ConfigChooser(multiSampling);
		}
		this.setEGLConfigChooser(this.mConfigChooser);

		this.setOnTouchListener(pEngine);
		this.mEngineRenderer = new EngineRenderer(pEngine, this.mConfigChooser, pRendererListener);
		this.setRenderer(this.mEngineRenderer);
	}
	
	public boolean isPause(){
		return this.mEngineRenderer.isPause();
	}

	public void doResume(){
		if (this.mEngineRenderer != null) {
			this.mEngineRenderer.onResume();
		}
	}
	
	public void doPause(){
		if (this.mEngineRenderer != null) {
			this.mEngineRenderer.onPause();
		}
	}
	
	
	
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}