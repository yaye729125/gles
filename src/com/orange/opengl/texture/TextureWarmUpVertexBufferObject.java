package com.orange.opengl.texture;

import java.nio.FloatBuffer;

import com.orange.entity.sprite.UncoloredSprite;
import com.orange.opengl.shader.PositionTextureCoordinatesShaderProgram;
import com.orange.opengl.shader.constants.ShaderProgramConstants;
import com.orange.opengl.util.GLState;
import com.orange.opengl.vbo.DrawType;
import com.orange.opengl.vbo.VertexBufferObject;
import com.orange.opengl.vbo.attribute.VertexBufferObjectAttributes;
import com.orange.opengl.vbo.attribute.VertexBufferObjectAttributesBuilder;

import android.opengl.GLES20;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class TextureWarmUpVertexBufferObject extends VertexBufferObject {
	// ===========================================================
	// Constants
	// ===========================================================

	public static final int VERTEX_INDEX_X = 0;
	public static final int VERTEX_INDEX_Y = UncoloredSprite.VERTEX_INDEX_X + 1;
	public static final int TEXTURECOORDINATES_INDEX_U = UncoloredSprite.VERTEX_INDEX_Y + 1;
	public static final int TEXTURECOORDINATES_INDEX_V = UncoloredSprite.TEXTURECOORDINATES_INDEX_U + 1;

	public static final int VERTEX_SIZE = 2 + 2;
	public static final int VERTICES_PER_VERTEXBUFFEROBJECT_SIZE = 3;
	public static final int VERTEXBUFFEROBJECT_SIZE = TextureWarmUpVertexBufferObject.VERTEX_SIZE * TextureWarmUpVertexBufferObject.VERTICES_PER_VERTEXBUFFEROBJECT_SIZE;

	public static final VertexBufferObjectAttributes VERTEXBUFFEROBJECTATTRIBUTES_DEFAULT = new VertexBufferObjectAttributesBuilder(2)
		.add(ShaderProgramConstants.ATTRIBUTE_POSITION_LOCATION, ShaderProgramConstants.ATTRIBUTE_POSITION, 2, GLES20.GL_FLOAT, false)
		.add(ShaderProgramConstants.ATTRIBUTE_TEXTURECOORDINATES_LOCATION, ShaderProgramConstants.ATTRIBUTE_TEXTURECOORDINATES, 2, GLES20.GL_FLOAT, false)
		.build();

	// ===========================================================
	// Fields
	// ===========================================================

	protected final FloatBuffer mFloatBuffer;

	// ===========================================================
	// Constructors
	// ===========================================================

	public TextureWarmUpVertexBufferObject() {
		super(null, VERTEXBUFFEROBJECT_SIZE, DrawType.STATIC, true, TextureWarmUpVertexBufferObject.VERTEXBUFFEROBJECTATTRIBUTES_DEFAULT);

		this.mFloatBuffer = this.mByteBuffer.asFloatBuffer();

		this.mFloatBuffer.put(0 * VERTEX_SIZE +  VERTEX_INDEX_X, 0);
		this.mFloatBuffer.put(0 * VERTEX_SIZE + VERTEX_INDEX_Y, 0);
		this.mFloatBuffer.put(0 * VERTEX_SIZE + TEXTURECOORDINATES_INDEX_U, 0);
		this.mFloatBuffer.put(0 * VERTEX_SIZE + TEXTURECOORDINATES_INDEX_V, 0);

		this.mFloatBuffer.put(1 * VERTEX_SIZE + VERTEX_INDEX_X, 1);
		this.mFloatBuffer.put(1 * VERTEX_SIZE + VERTEX_INDEX_Y, 0);
		this.mFloatBuffer.put(1 * VERTEX_SIZE + TEXTURECOORDINATES_INDEX_U, 1);
		this.mFloatBuffer.put(1 * VERTEX_SIZE + TEXTURECOORDINATES_INDEX_V, 0);

		this.mFloatBuffer.put(2 * VERTEX_SIZE + VERTEX_INDEX_X, 0);
		this.mFloatBuffer.put(2 * VERTEX_SIZE + VERTEX_INDEX_Y, 1);
		this.mFloatBuffer.put(2 * VERTEX_SIZE + TEXTURECOORDINATES_INDEX_U, 0);
		this.mFloatBuffer.put(2 * VERTEX_SIZE + TEXTURECOORDINATES_INDEX_V, 1);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public int getHeapMemoryByteSize() {
		return 0;
	}

	@Override
	public int getNativeHeapMemoryByteSize() {
		return this.getByteCapacity();
	}

	@Override
	protected void onBufferData() {
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, this.mByteBuffer.limit(), this.mByteBuffer, this.mUsage);
	}

	public void warmup(final GLState pGLState, final ITexture pTexture) {
		pTexture.bind(pGLState);
		this.bind(pGLState, PositionTextureCoordinatesShaderProgram.getInstance());

		pGLState.pushModelViewGLMatrix();
		{
			/* Far far away and really small. */
			pGLState.loadModelViewGLMatrixIdentity();
			pGLState.translateModelViewGLMatrixf(1000000, 1000000, 0);
			pGLState.scaleModelViewGLMatrixf(0.0001f, 0.0001f, 0);
	
			this.draw(GLES20.GL_TRIANGLES, VERTICES_PER_VERTEXBUFFEROBJECT_SIZE);
		}
		pGLState.popModelViewGLMatrix();

		this.unbind(pGLState, PositionTextureCoordinatesShaderProgram.getInstance());
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
