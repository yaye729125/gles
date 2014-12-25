package com.orange.opengl.util.criteria;

import com.orange.opengl.util.GLState;
import com.orange.util.adt.data.operator.IntOperator;

import android.os.Build;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class AndroidVersionCodeGLCriteria extends IntGLCriteria {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public AndroidVersionCodeGLCriteria(final IntOperator pIntOperator, final int pAndroidVersionCode) {
		super(pIntOperator, pAndroidVersionCode);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected int getActualCriteria(final GLState pGLState) {
		return Build.VERSION.SDK_INT;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
