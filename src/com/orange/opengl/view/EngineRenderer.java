package com.orange.opengl.view;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.orange.engine.Engine;
import com.orange.engine.options.RenderOptions;
import com.orange.opengl.util.GLState;
import com.orange.util.debug.Debug;

/**
 * (c) OrangeGame 2012
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class EngineRenderer implements GLSurfaceView.Renderer {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	final Engine mEngine;
	final ConfigChooser mConfigChooser;
	final boolean mMultiSampling;
	final IRendererListener mRendererListener;
	final GLState mGLState;
	final RendererLock mRendererLock = new RendererLock(false);

	// ===========================================================
	// Constructors
	// ===========================================================

	public EngineRenderer(final Engine pEngine, final ConfigChooser pConfigChooser, final IRendererListener pRendererListener) {
		this.mEngine = pEngine;
		this.mConfigChooser = pConfigChooser;
		this.mRendererListener = pRendererListener;
		this.mGLState = new GLState();
		this.mMultiSampling = this.mEngine.getEngineOptions().getRenderOptions().isMultiSampling();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onSurfaceCreated(final GL10 pGL, final EGLConfig pEGLConfig) {
		synchronized(GLState.class) {
			final RenderOptions renderOptions = this.mEngine.getEngineOptions().getRenderOptions();
			this.mGLState.reset(renderOptions, this.mConfigChooser, pEGLConfig);

			// TODO Check if available and make available through EngineOptions-RenderOptions
//			GLES20.glEnable(GLES20.GL_POLYGON_SMOOTH);
//			GLES20.glHint(GLES20.GL_POLYGON_SMOOTH_HINT, GLES20.GL_NICEST);
//			GLES20.glEnable(GLES20.GL_LINE_SMOOTH);
//			GLES20.glHint(GLES20.GL_LINE_SMOOTH_HINT, GLES20.GL_NICEST);
//			GLES20.glEnable(GLES20.GL_POINT_SMOOTH);
//			GLES20.glHint(GLES20.GL_POINT_SMOOTH_HINT, GLES20.GL_NICEST);

			this.mGLState.disableDepthTest();
			this.mGLState.enableBlend();
			this.mGLState.setDitherEnabled(renderOptions.isDithering());

			/* Enabling culling doesn't really make sense, because triangles are never drawn 'backwards' on purpose. */
//			this.mGLState.enableCulling();
//			GLES20.glFrontFace(GLES20.GL_CCW);
//			GLES20.glCullFace(GLES20.GL_BACK);

			if(this.mRendererListener != null) {
				this.mRendererListener.onSurfaceCreated(this.mGLState);
			}
		}
	}

	@Override
	public void onSurfaceChanged(final GL10 pGL, final int pWidth, final int pHeight) {
		this.mEngine.setSurfaceSize(pWidth, pHeight);
		GLES20.glViewport(0, 0, pWidth, pHeight);
		this.mGLState.loadProjectionGLMatrixIdentity();

		if(this.mRendererListener != null) {
			this.mRendererListener.onSurfaceChanged(this.mGLState, pWidth, pHeight);
		}
	}

	@Override
	public void onDrawFrame(final GL10 pGL) {
		synchronized(GLState.class) {
			if (this.mMultiSampling && this.mConfigChooser.isCoverageMultiSampling()) {
				final int GL_COVERAGE_BUFFER_BIT_NV = 0x8000;
				GLES20.glClear(GL_COVERAGE_BUFFER_BIT_NV);
			}
			try {
				this.mEngine.onDrawFrame(this.mGLState);
			} catch (final InterruptedException e) {
				Debug.e("GLThread interrupted!", e);
			}
			try {
				this.mRendererLock.lock();
				this.mRendererLock.waitUntilCanDraw();
				this.mRendererLock.unlock();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public boolean isPause(){
		return !this.mRendererLock.canDraw();
	}
	
	public void onResume(){
		try {
			this.mRendererLock.lock();
			this.mRendererLock.notifyCanDraw();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			this.mRendererLock.unlock();
		}
	}
	
	public void onPause(){
		try {
			this.mRendererLock.lock();
			this.mRendererLock.notifyCannotDraw();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			this.mRendererLock.unlock();
		}
	}
		
	
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	
	
	

	public static class RendererLock extends ReentrantLock {
		// ===========================================================
		// Constants
		// ===========================================================

		private static final long serialVersionUID = 8002964393548453966L;

		// ===========================================================
		// Fields
		// ===========================================================

		/**
		 * 
		 */
		
		final Condition mDrawingCondition = this.newCondition();
		final AtomicBoolean mDrawing = new AtomicBoolean(true);

		// ===========================================================
		// Constructors
		// ===========================================================

		public RendererLock(final boolean pFair) {
			super(pFair);
		}

		// ===========================================================
		// Getter & Setter
		// ===========================================================

		// ===========================================================
		// Methods for/from SuperClass/Interfaces
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================

		void signalAll(){
			this.mDrawingCondition.signalAll();
		}
		
		void notifyCanDraw() {
			this.mDrawing.set(true);
			this.mDrawingCondition.signalAll();
		}

		void notifyCannotDraw() {
			this.mDrawing.set(false);
		}

		void waitUntilCanDraw() throws InterruptedException {
			while(!this.mDrawing.get()) {
				this.mDrawingCondition.await();
			}
		}
		
		boolean canDraw(){
			return this.mDrawing.get();
		}

		// ===========================================================
		// Inner and Anonymous Classes
		// ===========================================================
	}
}