package com.orange.engine.options.resolutionpolicy;

import com.orange.opengl.view.RenderSurfaceView;

import android.view.View.MeasureSpec;

/**
 * RelativeResolutionPolicy：根据构建时的缩放参数，缩放游戏屏幕为指定比例
 * (c) OrangeGame 2012
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class RelativeResolutionPolicy extends BaseResolutionPolicy {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final float mWidthScale;
	private final float mHeightScale;

	// ===========================================================
	// Constructors
	// ===========================================================

	public RelativeResolutionPolicy(final float pScale) {
		this(pScale, pScale);
	}

	public RelativeResolutionPolicy(final float pWidthScale, final float pHeightScale) {
		this.mWidthScale = pWidthScale;
		this.mHeightScale = pHeightScale;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onMeasure(final RenderSurfaceView pRenderSurfaceView, final int pWidthMeasureSpec, final int pHeightMeasureSpec) {
		BaseResolutionPolicy.throwOnNotMeasureSpecEXACTLY(pWidthMeasureSpec, pHeightMeasureSpec);

		final int measuredWidth = (int)(MeasureSpec.getSize(pWidthMeasureSpec) * this.mWidthScale);
		final int measuredHeight = (int)(MeasureSpec.getSize(pHeightMeasureSpec) * this.mHeightScale);

		pRenderSurfaceView.setMeasuredDimensionProxy(measuredWidth, measuredHeight);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
