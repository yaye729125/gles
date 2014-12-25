package com.orange.opengl.texture;

import java.io.IOException;

import com.orange.opengl.util.GLState;

import android.opengl.GLES20;

/**
 * (c) OrangeGame 2012
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface ITexture {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public int getWidth();
	public int getHeight();

	public int getHardwareTextureID();

	public boolean isLoadedToHardware();
	public void setNotLoadedToHardware();

	public boolean isUpdateOnHardwareNeeded();
	public void setUpdateOnHardwareNeeded(final boolean pUpdateOnHardwareNeeded);

	/**
	 * @see {@link TextureManager#loadTexture(ITexture)}.
	 */
	public void load();
	/**
	 * @see {@link TextureManager#loadTexture(GLState, ITexture)}.
	 */
	public void load(final GLState pGLState) throws IOException;
	/**
	 * @see {@link TextureManager#unloadTexture(ITexture)}.
	 */
	public void unload();
	/**
	 * @see {@link TextureManager#unloadTexture(GLState, ITexture)}.
	 */
	public void unload(final GLState pGLState);

	public void loadToHardware(final GLState pGLState) throws IOException;
	public void unloadFromHardware(final GLState pGLState);
	public void reloadToHardware(final GLState pGLState) throws IOException;

	public void bind(final GLState pGLState);
	/**
	 * @param pGLActiveTexture from {@link GLES20#GL_TEXTURE0} to {@link GLES20#GL_TEXTURE31}. 
	 */
	public void bind(final GLState pGLState, final int pGLActiveTexture);

	public PixelFormat getPixelFormat();
	public TextureOptions getTextureOptions();

	public boolean hasTextureStateListener();
	public ITextureStateListener getTextureStateListener();
	public void setTextureStateListener(final ITextureStateListener pTextureStateListener);

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
}