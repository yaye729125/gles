package com.orange.opengl.texture.compressed.pvr.pixelbufferstrategy;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;

import com.orange.opengl.texture.PixelFormat;
import com.orange.opengl.texture.compressed.pvr.PVRTexture;
import com.orange.opengl.texture.compressed.pvr.PVRTexture.PVRTextureHeader;

import android.opengl.GLES20;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class GreedyPVRTexturePixelBufferStrategy implements IPVRTexturePixelBufferStrategy {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public IPVRTexturePixelBufferStrategyBufferManager newPVRTexturePixelBufferStrategyManager(final PVRTexture pPVRTexture) throws IOException {
		return new GreedyPVRTexturePixelBufferStrategyBufferManager(pPVRTexture);
	}

	@Override
	public void loadPVRTextureData(final IPVRTexturePixelBufferStrategyBufferManager pPVRTexturePixelBufferStrategyManager, final int pWidth, final int pHeight, final int pBytesPerPixel, final PixelFormat pPixelFormat, final int pLevel, final int pCurrentPixelDataOffset, final int pCurrentPixelDataSize) throws IOException {
		/* Adjust buffer. */
		final Buffer pixelBuffer = pPVRTexturePixelBufferStrategyManager.getPixelBuffer(PVRTextureHeader.SIZE + pCurrentPixelDataOffset, pCurrentPixelDataSize);

		/* Send to hardware. */
		GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, pLevel, pPixelFormat.getGLInternalFormat(), pWidth, pHeight, 0, pPixelFormat.getGLFormat(), pPixelFormat.getGLType(), pixelBuffer);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	public static class GreedyPVRTexturePixelBufferStrategyBufferManager implements IPVRTexturePixelBufferStrategyBufferManager {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Fields
		// ===========================================================

		private final ByteBuffer mByteBuffer;

		// ===========================================================
		// Constructors
		// ===========================================================

		public GreedyPVRTexturePixelBufferStrategyBufferManager(final PVRTexture pPVRTexture) throws IOException {
			this.mByteBuffer = pPVRTexture.getPVRTextureBuffer();
		}

		// ===========================================================
		// Getter & Setter
		// ===========================================================

		// ===========================================================
		// Methods for/from SuperClass/Interfaces
		// ===========================================================

		@Override
		public ByteBuffer getPixelBuffer(final int pStart, final int pByteCount) {
			this.mByteBuffer.position(pStart);
			this.mByteBuffer.limit(pStart + pByteCount);

			return this.mByteBuffer.slice();
		}

		// ===========================================================
		// Methods
		// ===========================================================

		// ===========================================================
		// Inner and Anonymous Classes
		// ===========================================================
	}
}