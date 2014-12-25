package com.orange.opengl.util;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class VertexUtils {
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

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * @param pVertices
	 * @param pVertexOffset
	 * @param pVertexStride
	 * @param pVertexIndex
	 * @return the value of the <code>pVertexOffset</code>-th attribute of the <code>pVertexIndex</code>-th vertex. 
	 */
	public static float getVertex(final float[] pVertices, final int pVertexOffset, final int pVertexStride, final int pVertexIndex) {
		return pVertices[(pVertexIndex * pVertexStride) + pVertexOffset];
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
