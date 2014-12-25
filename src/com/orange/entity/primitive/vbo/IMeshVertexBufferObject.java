package com.orange.entity.primitive.vbo;

import com.orange.entity.primitive.Mesh;
import com.orange.opengl.vbo.IVertexBufferObject;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IMeshVertexBufferObject extends IVertexBufferObject {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public float[] getBufferData();
	public void onUpdateColor(final Mesh pMesh);
	public void onUpdateVertices(final Mesh pMesh);
}