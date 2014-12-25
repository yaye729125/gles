package com.orange.entity.sprite.vbo;

import com.orange.entity.sprite.Sprite;
import com.orange.opengl.vbo.IVertexBufferObject;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface ISpriteVertexBufferObject extends IVertexBufferObject {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void onUpdateColor(final Sprite pSprite);
	public void onUpdateVertices(final Sprite pSprite);
	public void onUpdateTextureCoordinates(final Sprite pSprite);
}