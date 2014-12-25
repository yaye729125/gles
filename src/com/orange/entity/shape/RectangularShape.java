package com.orange.entity.shape;

import com.orange.engine.camera.Camera;
import com.orange.entity.primitive.Line;
import com.orange.opengl.shader.ShaderProgram;
import com.orange.util.algorithm.collision.RectangularShapeCollisionChecker;

/**
 * (c) OrangeGame 2012
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public abstract class RectangularShape extends Shape{
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================


	// ===========================================================
	// Constructors
	// ===========================================================

	public RectangularShape(final float pX, final float pY, final float pWidth, final float pHeight, final ShaderProgram pShaderProgram) {
		super(pX, pY, pWidth, pHeight, pShaderProgram);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	@Override
	public void setWidth(final float pWidth) {
		super.setWidth(pWidth);
		this.onUpdateVertices();
	}

	@Override
	public void setHeight(final float pHeight) {
		super.setHeight(pHeight);
		this.onUpdateVertices();
	}

	@Override
	public void setSize(final float pWidth, final float pHeight) {
		super.setSize(pWidth, pHeight);
		this.onUpdateVertices();
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public boolean isCulled(final Camera pCamera) {
		return !RectangularShapeCollisionChecker.isVisible(pCamera, this);
	}

	@Override
	public void reset() {
		super.reset();
	}

	@Override
	public boolean contains(final float pX, final float pY) {
		return RectangularShapeCollisionChecker.checkContains(this, pX, pY);
	}

	@Override
	public float[] getSceneCenterCoordinates() {
		return this.convertLocalToSceneCoordinates(this.mWidth * 0.5f, this.mHeight * 0.5f);
	}

	@Override
	public float[] getSceneCenterCoordinates(final float[] pReuse) {
		return this.convertLocalToSceneCoordinates(this.mWidth * 0.5f, this.mHeight * 0.5f, pReuse);
	}

	@Override
	public boolean collidesWith(final IShape pOtherShape) {
		if(pOtherShape instanceof RectangularShape) {
			return RectangularShapeCollisionChecker.checkCollision(this, (RectangularShape) pOtherShape);
		} else if(pOtherShape instanceof Line) {
			return RectangularShapeCollisionChecker.checkCollision(this, (Line) pOtherShape);
		} else {
			return false;
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
