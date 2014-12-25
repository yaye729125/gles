package com.orange.opengl.vbo.attribute;

import com.orange.opengl.GLES20Fix;

/**
 * The {@link VertexBufferObjectAttributeFix} is a special 
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class VertexBufferObjectAttributeFix extends VertexBufferObjectAttribute {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public VertexBufferObjectAttributeFix(final int pLocation, final String pName, final int pSize, final int pType, final boolean pNormalized, final int pOffset) {
		super(pLocation, pName, pSize, pType, pNormalized, pOffset);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void glVertexAttribPointer(final int pStride) {
		GLES20Fix.glVertexAttribPointer(this.mLocation, this.mSize, this.mType, this.mNormalized, pStride, this.mOffset);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}