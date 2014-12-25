package com.orange.entity.text.vbo;

import com.orange.entity.text.Text;
import com.orange.opengl.vbo.IVertexBufferObject;

/**
 * 
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface ITextVertexBufferObject extends IVertexBufferObject {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void onUpdateColor(final Text pText);
	public void onUpdateVertices(final Text pText);
}

