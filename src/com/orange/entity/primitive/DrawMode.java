package com.orange.entity.primitive;

import android.opengl.GLES20;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public enum DrawMode {
	// ===========================================================
	// Elements
	// ===========================================================

	POINTS(GLES20.GL_POINTS),
	LINE_STRIP(GLES20.GL_LINE_STRIP),
	LINE_LOOP(GLES20.GL_LINE_LOOP),
	LINES(GLES20.GL_LINES),
	TRIANGLE_STRIP(GLES20.GL_TRIANGLE_STRIP),
	TRIANGLE_FAN(GLES20.GL_TRIANGLE_FAN),
	TRIANGLES(GLES20.GL_TRIANGLES);

	// ===========================================================
	// Constants
	// ===========================================================

	public final int mDrawMode;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	private DrawMode(final int pDrawMode) {
		this.mDrawMode = pDrawMode;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public int getDrawMode() {
		return this.mDrawMode;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}