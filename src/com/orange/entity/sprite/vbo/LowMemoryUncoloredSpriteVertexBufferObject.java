package com.orange.entity.sprite.vbo;

import java.nio.FloatBuffer;

import com.orange.entity.sprite.Sprite;
import com.orange.opengl.texture.region.ITextureRegion;
import com.orange.opengl.vbo.DrawType;
import com.orange.opengl.vbo.VertexBufferObjectManager;
import com.orange.opengl.vbo.attribute.VertexBufferObjectAttributes;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class LowMemoryUncoloredSpriteVertexBufferObject extends LowMemorySpriteVertexBufferObject implements IUncoloredSpriteVertexBufferObject {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public LowMemoryUncoloredSpriteVertexBufferObject(final VertexBufferObjectManager pVertexBufferObjectManager, final int pCapacity, final DrawType pDrawType, final boolean pAutoDispose, final VertexBufferObjectAttributes pVertexBufferObjectAttributes) {
		super(pVertexBufferObjectManager, pCapacity, pDrawType, pAutoDispose, pVertexBufferObjectAttributes);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onUpdateVertices(final Sprite pSprite) {
		final FloatBuffer bufferData = this.mFloatBuffer;

		final float x = 0;
		final float y = 0;
		final float x2 = pSprite.getWidth(); // TODO Optimize with field access?
		final float y2 = pSprite.getHeight(); // TODO Optimize with field access?

		final float xCenter = (x + x2) * 0.5f;
		final float yCenter = (y + y2) * 0.5f;

		bufferData.put(0 * Sprite.VERTEX_SIZE + Sprite.VERTEX_INDEX_X, x);
		bufferData.put(0 * Sprite.VERTEX_SIZE + Sprite.VERTEX_INDEX_Y, yCenter);

		bufferData.put(1 * Sprite.VERTEX_SIZE + Sprite.VERTEX_INDEX_X, xCenter);
		bufferData.put(1 * Sprite.VERTEX_SIZE + Sprite.VERTEX_INDEX_Y, y2);

		bufferData.put(2 * Sprite.VERTEX_SIZE + Sprite.VERTEX_INDEX_X, xCenter);
		bufferData.put(2 * Sprite.VERTEX_SIZE + Sprite.VERTEX_INDEX_Y, y);

		bufferData.put(3 * Sprite.VERTEX_SIZE + Sprite.VERTEX_INDEX_X, x2);
		bufferData.put(3 * Sprite.VERTEX_SIZE + Sprite.VERTEX_INDEX_Y, yCenter);

		this.setDirtyOnHardware();
	}

	@Override
	public void onUpdateTextureCoordinates(final Sprite pSprite) {
		final FloatBuffer bufferData = this.mFloatBuffer;

		final ITextureRegion textureRegion = pSprite.getTextureRegion();

		final float u;
		final float v;
		final float u2;
		final float v2;

		if(pSprite.isFlippedVertical()) { // TODO Optimize with field access?
			if(pSprite.isFlippedHorizontal()) { // TODO Optimize with field access?
				u = textureRegion.getU2();
				u2 = textureRegion.getU();
				v = textureRegion.getV2();
				v2 = textureRegion.getV();
			} else {
				u = textureRegion.getU();
				u2 = textureRegion.getU2();
				v = textureRegion.getV2();
				v2 = textureRegion.getV();
			}
		} else {
			if(pSprite.isFlippedHorizontal()) { // TODO Optimize with field access?
				u = textureRegion.getU2();
				u2 = textureRegion.getU();
				v = textureRegion.getV();
				v2 = textureRegion.getV2();
			} else {
				u = textureRegion.getU();
				u2 = textureRegion.getU2();
				v = textureRegion.getV();
				v2 = textureRegion.getV2();
			}
		}

		final float uCenter = (u + u2) * 0.5f;
		final float vCenter = (v + v2) * 0.5f;

		if(textureRegion.isRotated()) {
			bufferData.put(0 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_U, uCenter);
			bufferData.put(0 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_V, v);

			bufferData.put(1 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_U, u);
			bufferData.put(1 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_V, vCenter);

			bufferData.put(2 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_U, u2);
			bufferData.put(2 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_V, vCenter);

			bufferData.put(3 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_U, uCenter);
			bufferData.put(3 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_V, v2);
		} else {
			bufferData.put(0 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_U, u);
			bufferData.put(0 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_V, vCenter);

			bufferData.put(1 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_U, uCenter);
			bufferData.put(1 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_V, v2);

			bufferData.put(2 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_U, uCenter);
			bufferData.put(2 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_V, v);

			bufferData.put(3 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_U, u2);
			bufferData.put(3 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_V, vCenter);
		}

		this.setDirtyOnHardware();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}