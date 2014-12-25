package com.orange.entity.primitive.vbo;

import com.orange.entity.primitive.Rectangle;
import com.orange.opengl.vbo.IVertexBufferObject;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IRectangleVertexBufferObject extends IVertexBufferObject {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void onUpdateColor(final Rectangle pRectangle);
	public void onUpdateVertices(final Rectangle pRectangle);
}