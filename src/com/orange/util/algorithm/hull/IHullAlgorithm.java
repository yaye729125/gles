package com.orange.util.algorithm.hull;


/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IHullAlgorithm {
	// ===========================================================
	// Final Fields
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * @param pVertices a interleaved float[] containing vertex data.
	 * @param pVertexCount the amount of vertices to look at in pVertices.
	 * @param pVertexOffsetX the offset of the x-coordinate in a vertex.
	 * @param pVertexOffsetY the offset of the y-coordinate in a vertex.
	 * @param pStride the stride between each vertex.
	 * @return
	 */
	public int computeHull(final float[] pVertices, final int pVertexCount, final int pVertexOffsetX, final int pVertexOffsetY, final int pStride);
}
