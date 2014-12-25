package com.orange.util.algorithm.hull;


/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class HullUtils {
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

	public static int indexOfLowestVertex(final float[] pVertices, final int pVertexCount, final int pVertexOffsetY, final int pVertexStride) {
		/* Simply choose 0 as the first candidate. */
		int lowestVertexIndex = 0;
		float lowestVertexY = pVertices[pVertexOffsetY];

		final int lastVertexOffset = pVertexCount * pVertexStride;
		
		/* Starting at one since, since we chose 0 as a the first candidate. */
		int currentVertexIndex = 1; 
		int currentVertexOffsetY = pVertexStride + pVertexOffsetY;
		
		/* Loop to the end. */
		while(currentVertexOffsetY < lastVertexOffset) {
			final float currentVertexY = pVertices[currentVertexOffsetY];

			/* Check if the current candidate is lower and if so, assign it. */
			if(currentVertexY < lowestVertexY) {
				lowestVertexIndex = currentVertexIndex;
				lowestVertexY = currentVertexY;
			}
			currentVertexIndex++;
			currentVertexOffsetY += pVertexStride;
		}
		return lowestVertexIndex;
	}

	public static void swap(final float[] pVertices, final int pVertexStride, final int pVertexIndexA, final int pVertexIndexB) {
		final int vertexOffsetA = pVertexIndexA * pVertexStride;
		final int vertexOffsetB = pVertexIndexB * pVertexStride;

		for(int i = pVertexStride - 1; i >= 0; i--) {
			final float tmp = pVertices[vertexOffsetA + i];
			pVertices[vertexOffsetA + i] = pVertices[vertexOffsetB + i];
			pVertices[vertexOffsetB + i] = tmp;
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
