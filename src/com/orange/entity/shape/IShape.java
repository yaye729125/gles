package com.orange.entity.shape;

import com.orange.entity.IEntity;
import com.orange.entity.scene.ITouchArea;
import com.orange.opengl.shader.ShaderProgram;
import com.orange.opengl.vbo.IVertexBufferObject;
import com.orange.opengl.vbo.VertexBufferObjectManager;

import android.opengl.GLES20;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IShape extends IEntity, ITouchArea {
	// ===========================================================
	// Constants
	// ===========================================================

	public static final int BLENDFUNCTION_SOURCE_DEFAULT = GLES20.GL_SRC_ALPHA;
	public static final int BLENDFUNCTION_DESTINATION_DEFAULT = GLES20.GL_ONE_MINUS_SRC_ALPHA;

	public static final int BLENDFUNCTION_SOURCE_PREMULTIPLYALPHA_DEFAULT = GLES20.GL_ONE;
	public static final int BLENDFUNCTION_DESTINATION_PREMULTIPLYALPHA_DEFAULT = GLES20.GL_ONE_MINUS_SRC_ALPHA;

	// ===========================================================
	// Methods
	// ===========================================================

	public boolean collidesWith(final IShape pOtherShape);

	public boolean isBlendingEnabled();
	public void setBlendingEnabled(final boolean pBlendingEnabled);
	public int getBlendFunctionSource();
	public int getBlendFunctionDestination();
	public void setBlendFunctionSource(final int pBlendFunctionSource);
	public void setBlendFunctionDestination(final int pBlendFunctionDestination);
	public void setBlendFunction(final int pBlendFunctionSource, final int pBlendFunctionDestination);

	public VertexBufferObjectManager getVertexBufferObjectManager();
	public IVertexBufferObject getVertexBufferObject();
	public ShaderProgram getShaderProgram();
	public void setShaderProgram(final ShaderProgram pShaderProgram);
}