package com.orange.opengl.texture.compressed.pvr;

import java.io.IOException;
import java.util.zip.GZIPInputStream;

import com.orange.opengl.texture.ITextureStateListener;
import com.orange.opengl.texture.TextureManager;
import com.orange.opengl.texture.TextureOptions;
import com.orange.opengl.texture.compressed.pvr.pixelbufferstrategy.IPVRTexturePixelBufferStrategy;

/**
 * (c) OrangeGame 2012
 * 
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public abstract class PVRGZTexture extends PVRTexture {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public PVRGZTexture(final TextureManager pTextureManager, final PVRTextureFormat pPVRTextureFormat) throws IllegalArgumentException, IOException {
		super(pTextureManager, pPVRTextureFormat);
	}

	public PVRGZTexture(final TextureManager pTextureManager, final PVRTextureFormat pPVRTextureFormat, final IPVRTexturePixelBufferStrategy pPVRTexturePixelBufferStrategy) throws IllegalArgumentException, IOException {
		super(pTextureManager, pPVRTextureFormat, pPVRTexturePixelBufferStrategy);
	}

	public PVRGZTexture(final TextureManager pTextureManager, final PVRTextureFormat pPVRTextureFormat, final ITextureStateListener pTextureStateListener) throws IllegalArgumentException, IOException {
		super(pTextureManager, pPVRTextureFormat, pTextureStateListener);
	}

	public PVRGZTexture(final TextureManager pTextureManager, final PVRTextureFormat pPVRTextureFormat, final IPVRTexturePixelBufferStrategy pPVRTexturePixelBufferStrategy, final ITextureStateListener pTextureStateListener) throws IllegalArgumentException, IOException {
		super(pTextureManager, pPVRTextureFormat, pPVRTexturePixelBufferStrategy, pTextureStateListener);
	}

	public PVRGZTexture(final TextureManager pTextureManager, final PVRTextureFormat pPVRTextureFormat, final TextureOptions pTextureOptions) throws IllegalArgumentException, IOException {
		super(pTextureManager, pPVRTextureFormat, pTextureOptions);
	}

	public PVRGZTexture(final TextureManager pTextureManager, final PVRTextureFormat pPVRTextureFormat, final IPVRTexturePixelBufferStrategy pPVRTexturePixelBufferStrategy, final TextureOptions pTextureOptions) throws IllegalArgumentException, IOException {
		super(pTextureManager, pPVRTextureFormat, pPVRTexturePixelBufferStrategy, pTextureOptions);
	}

	public PVRGZTexture(final TextureManager pTextureManager, final PVRTextureFormat pPVRTextureFormat, final TextureOptions pTextureOptions, final ITextureStateListener pTextureStateListener) throws IllegalArgumentException, IOException {
		super(pTextureManager, pPVRTextureFormat, pTextureOptions, pTextureStateListener);
	}

	public PVRGZTexture(final TextureManager pTextureManager, final PVRTextureFormat pPVRTextureFormat, final IPVRTexturePixelBufferStrategy pPVRTexturePixelBufferStrategy, final TextureOptions pTextureOptions, final ITextureStateListener pTextureStateListener) throws IllegalArgumentException, IOException {
		super(pTextureManager, pPVRTextureFormat, pPVRTexturePixelBufferStrategy, pTextureOptions, pTextureStateListener);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public GZIPInputStream getInputStream() throws IOException {
		return new GZIPInputStream(this.onGetInputStream());
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
