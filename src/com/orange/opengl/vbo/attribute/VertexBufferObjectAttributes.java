package com.orange.opengl.vbo.attribute;


/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class VertexBufferObjectAttributes {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final int mStride;
	private final VertexBufferObjectAttribute[] mVertexBufferObjectAttributes;

	// ===========================================================
	// Constructors
	// ===========================================================

	public VertexBufferObjectAttributes(final int pStride, final VertexBufferObjectAttribute ... pVertexBufferObjectAttributes) {
		this.mVertexBufferObjectAttributes = pVertexBufferObjectAttributes;
		this.mStride = pStride;
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

	public void glVertexAttribPointers() {
		final VertexBufferObjectAttribute[] vertexBufferObjectAttributes = this.mVertexBufferObjectAttributes;

		final int stride = this.mStride;

		final int vertexBufferObjectAttributeCount = vertexBufferObjectAttributes.length;
		for(int i = 0; i < vertexBufferObjectAttributeCount; i++) {
			vertexBufferObjectAttributes[i].glVertexAttribPointer(stride);
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}