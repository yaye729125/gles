package com.orange.entity.sprite.vbo;

import com.orange.entity.sprite.TiledSprite;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface ITiledSpriteVertexBufferObject extends ISpriteVertexBufferObject {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void onUpdateColor(final TiledSprite pTiledSprite);
	public void onUpdateVertices(final TiledSprite pTiledSprite);
	public void onUpdateTextureCoordinates(final TiledSprite pTiledSprite);
}