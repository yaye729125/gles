package com.orange.entity.sprite.vbo;

import com.orange.entity.sprite.Sprite;
import com.orange.entity.sprite.UniformColorSprite;
import com.orange.opengl.texture.region.ITextureRegion;
import com.orange.opengl.vbo.DrawType;
import com.orange.opengl.vbo.HighPerformanceVertexBufferObject;
import com.orange.opengl.vbo.VertexBufferObjectManager;
import com.orange.opengl.vbo.attribute.VertexBufferObjectAttributes;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class HighPerformanceUniformColorSpriteVertexBufferObject extends HighPerformanceVertexBufferObject implements IUniformColorSpriteVertexBufferObject {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public HighPerformanceUniformColorSpriteVertexBufferObject(final VertexBufferObjectManager pVertexBufferObjectManager, final int pCapacity, final DrawType pDrawType, final boolean pAutoDispose, final VertexBufferObjectAttributes pVertexBufferObjectAttributes) {
		super(pVertexBufferObjectManager, pCapacity, pDrawType, pAutoDispose, pVertexBufferObjectAttributes);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onUpdateColor(final Sprite pSprite) {
		/* Nothing to do, since color is applied as a uniform. */
	}

	@Override
	public void onUpdateVertices(final Sprite pSprite) {
		final float[] bufferData = this.mBufferData;

		final float x = 0;
		final float y = 0;
		final float x2 = pSprite.getWidth(); // TODO Optimize with field access?
		final float y2 = pSprite.getHeight(); // TODO Optimize with field access?

		bufferData[0 * UniformColorSprite.VERTEX_SIZE + UniformColorSprite.VERTEX_INDEX_X] = x;
		bufferData[0 * UniformColorSprite.VERTEX_SIZE + UniformColorSprite.VERTEX_INDEX_Y] = y;

		bufferData[1 * UniformColorSprite.VERTEX_SIZE + UniformColorSprite.VERTEX_INDEX_X] = x;
		bufferData[1 * UniformColorSprite.VERTEX_SIZE + UniformColorSprite.VERTEX_INDEX_Y] = y2;

		bufferData[2 * UniformColorSprite.VERTEX_SIZE + UniformColorSprite.VERTEX_INDEX_X] = x2;
		bufferData[2 * UniformColorSprite.VERTEX_SIZE + UniformColorSprite.VERTEX_INDEX_Y] = y;

		bufferData[3 * UniformColorSprite.VERTEX_SIZE + UniformColorSprite.VERTEX_INDEX_X] = x2;
		bufferData[3 * UniformColorSprite.VERTEX_SIZE + UniformColorSprite.VERTEX_INDEX_Y] = y2;

		this.setDirtyOnHardware();
	}

	@Override
	public void onUpdateTextureCoordinates(final Sprite pSprite) {
		final float[] bufferData = this.mBufferData;

		final ITextureRegion textureRegion = pSprite.getTextureRegion(); // TODO Optimize with field access?

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

		if(textureRegion.isRotated()) {
			bufferData[0 * UniformColorSprite.VERTEX_SIZE + UniformColorSprite.TEXTURECOORDINATES_INDEX_U] = u2;
			bufferData[0 * UniformColorSprite.VERTEX_SIZE + UniformColorSprite.TEXTURECOORDINATES_INDEX_V] = v;

			bufferData[1 * UniformColorSprite.VERTEX_SIZE + UniformColorSprite.TEXTURECOORDINATES_INDEX_U] = u;
			bufferData[1 * UniformColorSprite.VERTEX_SIZE + UniformColorSprite.TEXTURECOORDINATES_INDEX_V] = v;

			bufferData[2 * UniformColorSprite.VERTEX_SIZE + UniformColorSprite.TEXTURECOORDINATES_INDEX_U] = u2;
			bufferData[2 * UniformColorSprite.VERTEX_SIZE + UniformColorSprite.TEXTURECOORDINATES_INDEX_V] = v2;

			bufferData[3 * UniformColorSprite.VERTEX_SIZE + UniformColorSprite.TEXTURECOORDINATES_INDEX_U] = u;
			bufferData[3 * UniformColorSprite.VERTEX_SIZE + UniformColorSprite.TEXTURECOORDINATES_INDEX_V] = v2;
		} else {
			bufferData[0 * UniformColorSprite.VERTEX_SIZE + UniformColorSprite.TEXTURECOORDINATES_INDEX_U] = u;
			bufferData[0 * UniformColorSprite.VERTEX_SIZE + UniformColorSprite.TEXTURECOORDINATES_INDEX_V] = v;

			bufferData[1 * UniformColorSprite.VERTEX_SIZE + UniformColorSprite.TEXTURECOORDINATES_INDEX_U] = u;
			bufferData[1 * UniformColorSprite.VERTEX_SIZE + UniformColorSprite.TEXTURECOORDINATES_INDEX_V] = v2;

			bufferData[2 * UniformColorSprite.VERTEX_SIZE + UniformColorSprite.TEXTURECOORDINATES_INDEX_U] = u2;
			bufferData[2 * UniformColorSprite.VERTEX_SIZE + UniformColorSprite.TEXTURECOORDINATES_INDEX_V] = v;

			bufferData[3 * UniformColorSprite.VERTEX_SIZE + UniformColorSprite.TEXTURECOORDINATES_INDEX_U] = u2;
			bufferData[3 * UniformColorSprite.VERTEX_SIZE + UniformColorSprite.TEXTURECOORDINATES_INDEX_V] = v2;
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

