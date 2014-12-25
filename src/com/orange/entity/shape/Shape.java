package com.orange.entity.shape;

import com.orange.engine.camera.Camera;
import com.orange.entity.Entity;
import com.orange.input.touch.TouchEvent;
import com.orange.opengl.shader.ShaderProgram;
import com.orange.opengl.texture.ITexture;
import com.orange.opengl.texture.TextureOptions;
import com.orange.opengl.texture.region.ITextureRegion;
import com.orange.opengl.util.GLState;
import com.orange.opengl.vbo.IVertexBufferObject;
import com.orange.opengl.vbo.VertexBufferObjectManager;

/**
 * (c) OrangeGame 2012
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public abstract class Shape extends Entity implements IShape {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	protected int mBlendFunctionSource = IShape.BLENDFUNCTION_SOURCE_DEFAULT;
	protected int mBlendFunctionDestination = IShape.BLENDFUNCTION_DESTINATION_DEFAULT;

	protected boolean mBlendingEnabled = false;

	protected ShaderProgram mShaderProgram;

	// ===========================================================
	// Constructors
	// ===========================================================

	public Shape(final float pX, final float pY, final ShaderProgram pShaderProgram) {
		this(pX, pY, 0, 0, pShaderProgram);
	}
	
	public Shape(float pX, float pY, float pWidth, float pHeight, final ShaderProgram pShaderProgram) {
		super(pX, pY, pWidth, pHeight);
		// TODO Auto-generated constructor stub
		this.mShaderProgram = pShaderProgram;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================


	@Override
	public boolean isBlendingEnabled() {
		return this.mBlendingEnabled;
	}

	@Override
	public void setBlendingEnabled(final boolean pBlendingEnabled) {
		this.mBlendingEnabled = pBlendingEnabled;
	}

	@Override
	public int getBlendFunctionSource() {
		return this.mBlendFunctionSource;
	}

	@Override
	public void setBlendFunctionSource(final int pBlendFunctionSource) {
		this.mBlendFunctionSource = pBlendFunctionSource;
	}

	@Override
	public int getBlendFunctionDestination() {
		return this.mBlendFunctionDestination;
	}

	@Override
	public void setBlendFunctionDestination(final int pBlendFunctionDestination) {
		this.mBlendFunctionDestination = pBlendFunctionDestination;
	}

	@Override
	public void setBlendFunction(final int pBlendFunctionSource, final int pBlendFunctionDestination) {
		this.mBlendFunctionSource = pBlendFunctionSource;
		this.mBlendFunctionDestination = pBlendFunctionDestination;
	}

	@Override
	public ShaderProgram getShaderProgram() {
		return this.mShaderProgram;
	}

	@Override
	public void setShaderProgram(final ShaderProgram pShaderProgram) {
		this.mShaderProgram = pShaderProgram;
	}

	@Override
	public VertexBufferObjectManager getVertexBufferObjectManager() {
		return this.getVertexBufferObject().getVertexBufferObjectManager();
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	protected abstract void onUpdateVertices();

	@Override
	protected void preDraw(final GLState pGLState, final Camera pCamera) {
		if(this.mBlendingEnabled) {
			pGLState.enableBlend();
			pGLState.blendFunction(this.mBlendFunctionSource, this.mBlendFunctionDestination);
		}
	}

	@Override
	protected void postDraw(final GLState pGLState, final Camera pCamera) {
		if(this.mBlendingEnabled) {
			pGLState.disableBlend();
		}
	}

	@Override
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		return false;
	}

	@Override
	public void reset() {
		super.reset();

		this.mBlendFunctionSource = IShape.BLENDFUNCTION_SOURCE_DEFAULT;
		this.mBlendFunctionDestination = IShape.BLENDFUNCTION_DESTINATION_DEFAULT;
	}

	@Override
	public void dispose() {
		super.dispose();

		final IVertexBufferObject vertexBufferObject = this.getVertexBufferObject();
		if((vertexBufferObject != null) && vertexBufferObject.isAutoDispose() && !vertexBufferObject.isDisposed()) {
			vertexBufferObject.dispose();
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	protected void initBlendFunction(final ITextureRegion pTextureRegion) {
		this.initBlendFunction(pTextureRegion.getTexture());
	}

	protected void initBlendFunction(final ITexture pTexture) {
		this.initBlendFunction(pTexture.getTextureOptions());
	}

	protected void initBlendFunction(final TextureOptions pTextureOptions) {
		if(pTextureOptions.mPreMultiplyAlpha) {
			this.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_PREMULTIPLYALPHA_DEFAULT, IShape.BLENDFUNCTION_DESTINATION_PREMULTIPLYALPHA_DEFAULT);
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
