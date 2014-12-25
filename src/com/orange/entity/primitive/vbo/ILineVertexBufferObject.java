package com.orange.entity.primitive.vbo;

import com.orange.entity.primitive.Line;
import com.orange.opengl.vbo.IVertexBufferObject;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface ILineVertexBufferObject extends IVertexBufferObject {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void onUpdateColor(final Line pLine);
	public void onUpdateVertices(final Line pLine);
}