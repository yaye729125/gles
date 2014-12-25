package com.orange.entity.group.primitive.vbo;

import java.nio.FloatBuffer;

import com.orange.entity.group.primitive.RectangleGroup;
import com.orange.entity.primitive.Rectangle;
import com.orange.opengl.vbo.DrawType;
import com.orange.opengl.vbo.LowMemoryVertexBufferObject;
import com.orange.opengl.vbo.VertexBufferObjectManager;
import com.orange.opengl.vbo.attribute.VertexBufferObjectAttributes;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class LowMemoryEntityGroupVertexBufferObject extends LowMemoryVertexBufferObject implements IRectangleGroupVertexBufferObject {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public LowMemoryEntityGroupVertexBufferObject(final VertexBufferObjectManager pVertexBufferObjectManager, final int pCapacity, final DrawType pDrawType, final boolean pAutoDispose, final VertexBufferObjectAttributes pVertexBufferObjectAttributes) {
		super(pVertexBufferObjectManager, pCapacity, pDrawType, pAutoDispose, pVertexBufferObjectAttributes);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onUpdateColor(final RectangleGroup pRectangleGroup) {
		final FloatBuffer bufferData = this.mFloatBuffer;

		final float packedColor = pRectangleGroup.getColor().getABGRPackedFloat();

		bufferData.put(0 * Rectangle.VERTEX_SIZE + Rectangle.COLOR_INDEX, packedColor);
		bufferData.put(1 * Rectangle.VERTEX_SIZE + Rectangle.COLOR_INDEX, packedColor);
		bufferData.put(2 * Rectangle.VERTEX_SIZE + Rectangle.COLOR_INDEX, packedColor);
		bufferData.put(3 * Rectangle.VERTEX_SIZE + Rectangle.COLOR_INDEX, packedColor);

		this.setDirtyOnHardware();
	}

	@Override
	public void onUpdateVertices(final RectangleGroup pRectangleGroup) {
		final FloatBuffer bufferData = this.mFloatBuffer;

		final float x = 0;
		final float y = 0;
		final float x2 = pRectangleGroup.getWidth();
		final float y2 = pRectangleGroup.getHeight();

		bufferData.put(0 * Rectangle.VERTEX_SIZE + Rectangle.VERTEX_INDEX_X, x);
		bufferData.put(0 * Rectangle.VERTEX_SIZE + Rectangle.VERTEX_INDEX_Y, y);

		bufferData.put(1 * Rectangle.VERTEX_SIZE + Rectangle.VERTEX_INDEX_X, x);
		bufferData.put(1 * Rectangle.VERTEX_SIZE + Rectangle.VERTEX_INDEX_Y, y2);

		bufferData.put(2 * Rectangle.VERTEX_SIZE + Rectangle.VERTEX_INDEX_X, x2);
		bufferData.put(2 * Rectangle.VERTEX_SIZE + Rectangle.VERTEX_INDEX_Y, y);

		bufferData.put(3 * Rectangle.VERTEX_SIZE + Rectangle.VERTEX_INDEX_X, x2);
		bufferData.put(3 * Rectangle.VERTEX_SIZE + Rectangle.VERTEX_INDEX_Y, y2);

		this.setDirtyOnHardware();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}