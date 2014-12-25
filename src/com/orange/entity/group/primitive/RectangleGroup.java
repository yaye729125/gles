package com.orange.entity.group.primitive;

import com.orange.engine.camera.Camera;
import com.orange.entity.group.primitive.vbo.HighPerformanceEntityGroupVertexBufferObject;
import com.orange.entity.group.primitive.vbo.IRectangleGroupVertexBufferObject;
import com.orange.entity.primitive.vbo.HighPerformanceRectangleVertexBufferObject;
import com.orange.entity.shape.RectangularShape;
import com.orange.opengl.shader.PositionColorShaderProgram;
import com.orange.opengl.shader.constants.ShaderProgramConstants;
import com.orange.opengl.util.GLState;
import com.orange.opengl.vbo.DrawType;
import com.orange.opengl.vbo.VertexBufferObjectManager;
import com.orange.opengl.vbo.attribute.VertexBufferObjectAttribute;
import com.orange.opengl.vbo.attribute.VertexBufferObjectAttributes;
import com.orange.opengl.vbo.attribute.VertexBufferObjectAttributesBuilder;

import android.opengl.GLES20;

/**
 * (c) OrangeGame 2012
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class RectangleGroup extends RectangularShape {
	// ===========================================================
	// Constants
	// ===========================================================

	public static final int VERTEX_INDEX_X = 0;
	public static final int VERTEX_INDEX_Y = RectangleGroup.VERTEX_INDEX_X + 1;
	public static final int COLOR_INDEX = RectangleGroup.VERTEX_INDEX_Y + 1;

	public static final int VERTEX_SIZE = 2 + 1;
	public static final int VERTICES_PER_RECTANGLE = 4;
	public static final int RECTANGLE_SIZE = RectangleGroup.VERTEX_SIZE * RectangleGroup.VERTICES_PER_RECTANGLE;

	public static final VertexBufferObjectAttributes VERTEXBUFFEROBJECTATTRIBUTES_DEFAULT = new VertexBufferObjectAttributesBuilder(2)
		.add(ShaderProgramConstants.ATTRIBUTE_POSITION_LOCATION, ShaderProgramConstants.ATTRIBUTE_POSITION, 2, GLES20.GL_FLOAT, false)
		.add(ShaderProgramConstants.ATTRIBUTE_COLOR_LOCATION, ShaderProgramConstants.ATTRIBUTE_COLOR, 4, GLES20.GL_UNSIGNED_BYTE, true)
		.build();

	// ===========================================================
	// Fields
	// ===========================================================

	protected final IRectangleGroupVertexBufferObject mRectangleGroupVertexBufferObject;

	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * Uses a default {@link HighPerformanceRectangleVertexBufferObject} in {@link DrawType#STATIC} with the {@link VertexBufferObjectAttribute}s: {@link RectangleGroup#VERTEXBUFFEROBJECTATTRIBUTES_DEFAULT}.
	 */
	public RectangleGroup(final float pX, final float pY, final float pWidth, final float pHeight, final VertexBufferObjectManager pVertexBufferObjectManager) {
		this(pX, pY, pWidth, pHeight, pVertexBufferObjectManager, DrawType.STATIC);
	}

	/**
	 * Uses a default {@link HighPerformanceRectangleVertexBufferObject} with the {@link VertexBufferObjectAttribute}s: {@link RectangleGroup#VERTEXBUFFEROBJECTATTRIBUTES_DEFAULT}.
	 */
	public RectangleGroup(final float pX, final float pY, final float pWidth, final float pHeight, final VertexBufferObjectManager pVertexBufferObjectManager, final DrawType pDrawType) {
		this(pX, pY, pWidth, pHeight, new HighPerformanceEntityGroupVertexBufferObject(pVertexBufferObjectManager, RectangleGroup.RECTANGLE_SIZE, pDrawType, true, RectangleGroup.VERTEXBUFFEROBJECTATTRIBUTES_DEFAULT));
	}

	public RectangleGroup(final float pX, final float pY, final float pWidth, final float pHeight, final IRectangleGroupVertexBufferObject mEntityGroupVertexBufferObject) {
		super(pX, pY, pWidth, pHeight, PositionColorShaderProgram.getInstance());

		this.mRectangleGroupVertexBufferObject = mEntityGroupVertexBufferObject;
		this.setAlpha(0.0f);
		this.onUpdateVertices();
		this.onUpdateColor();

		this.setBlendingEnabled(true);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public IRectangleGroupVertexBufferObject getVertexBufferObject() {
		return this.mRectangleGroupVertexBufferObject;
	}

	@Override
	protected void preDraw(final GLState pGLState, final Camera pCamera) {
		super.preDraw(pGLState, pCamera);

		this.mRectangleGroupVertexBufferObject.bind(pGLState, this.mShaderProgram);
	}

	@Override
	protected void draw(final GLState pGLState, final Camera pCamera) {
		this.mRectangleGroupVertexBufferObject.draw(GLES20.GL_TRIANGLE_STRIP, RectangleGroup.VERTICES_PER_RECTANGLE);
	}

	@Override
	protected void postDraw(final GLState pGLState, final Camera pCamera) {
		this.mRectangleGroupVertexBufferObject.unbind(pGLState, this.mShaderProgram);

		super.postDraw(pGLState, pCamera);
	}

	@Override
	protected void onUpdateColor() {
		this.mRectangleGroupVertexBufferObject.onUpdateColor(this);
	}

	@Override
	protected void onUpdateVertices() {
		this.mRectangleGroupVertexBufferObject.onUpdateVertices(this);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}