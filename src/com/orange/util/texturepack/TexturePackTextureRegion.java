package com.orange.util.texturepack;

import com.orange.opengl.texture.ITexture;
import com.orange.opengl.texture.region.TextureRegion;

/**
 * 
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class TexturePackTextureRegion extends TextureRegion {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final int mID;
	private final String mSource;
	private final boolean mTrimmed;
	private final int mSourceX;
	private final int mSourceY;
	private final int mSourceWidth;
	private final int mSourceHeight;

	// ===========================================================
	// Constructors
	// ===========================================================

	public TexturePackTextureRegion(final ITexture pTexture, final int pX, final int pY, final int pWidth, final int pHeight, final int pID, final String pSource, final boolean pRotated, final boolean pTrimmed, final int pSourceX, final int pSourceY, final int pSourceWidth, final int pSourceHeight) {
		super(pTexture, pX, pY, pWidth, pHeight, pRotated);

		this.mID = pID;
		this.mSource = pSource;
		this.mTrimmed = pTrimmed;
		this.mSourceX = pSourceX;
		this.mSourceY = pSourceY;
		this.mSourceWidth = pSourceWidth;
		this.mSourceHeight = pSourceHeight;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public int getID() {
		return this.mID;
	}

	public String getSource() {
		return this.mSource;
	}

	public boolean isTrimmed() {
		return this.mTrimmed;
	}

	public int getSourceX() {
		return this.mSourceX;
	}

	public int getSourceY() {
		return this.mSourceY;
	}

	public int getSourceWidth() {
		return this.mSourceWidth;
	}

	public int getSourceHeight() {
		return this.mSourceHeight;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
