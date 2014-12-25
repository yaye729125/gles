package com.orange.entity.primitive.vbo;

import java.nio.FloatBuffer;

import com.orange.entity.primitive.Line;
import com.orange.opengl.vbo.DrawType;
import com.orange.opengl.vbo.LowMemoryVertexBufferObject;
import com.orange.opengl.vbo.VertexBufferObjectManager;
import com.orange.opengl.vbo.attribute.VertexBufferObjectAttributes;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class LowMemoryLineVertexBufferObject extends LowMemoryVertexBufferObject implements ILineVertexBufferObject {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public LowMemoryLineVertexBufferObject(final VertexBufferObjectManager pVertexBufferObjectManager, final int pCapacity, final DrawType pDrawType, final boolean pAutoDispose, final VertexBufferObjectAttributes pVertexBufferObjectAttributes) {
		super(pVertexBufferObjectManager, pCapacity, pDrawType, pAutoDispose, pVertexBufferObjectAttributes);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onUpdateColor(final Line pLine) {
		final FloatBuffer bufferData = this.mFloatBuffer;

		final float packedColor = pLine.getColor().getABGRPackedFloat();

		bufferData.put(0 * Line.VERTEX_SIZE + Line.COLOR_INDEX, packedColor);
		bufferData.put(1 * Line.VERTEX_SIZE + Line.COLOR_INDEX, packedColor);

		this.setDirtyOnHardware();
	}

	@Override
	public void onUpdateVertices(final Line pLine) {
		final FloatBuffer bufferData = this.mFloatBuffer;

		bufferData.put(0 * Line.VERTEX_SIZE + Line.VERTEX_INDEX_X, 0);
		bufferData.put(0 * Line.VERTEX_SIZE + Line.VERTEX_INDEX_Y, 0);

		bufferData.put(1 * Line.VERTEX_SIZE + Line.VERTEX_INDEX_X, pLine.getX2() - pLine.getX1()); // TODO Optimize with field access?
		bufferData.put(1 * Line.VERTEX_SIZE + Line.VERTEX_INDEX_Y, pLine.getY2() - pLine.getY1()); // TODO Optimize with field access?

		this.setDirtyOnHardware();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}