package com.orange.entity.group.primitive.vbo;

import com.orange.entity.group.primitive.RectangleGroup;
import com.orange.opengl.vbo.IVertexBufferObject;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IRectangleGroupVertexBufferObject extends IVertexBufferObject {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void onUpdateColor(final RectangleGroup pRectangleGroup);
	public void onUpdateVertices(final RectangleGroup pRectangleGroup);
}