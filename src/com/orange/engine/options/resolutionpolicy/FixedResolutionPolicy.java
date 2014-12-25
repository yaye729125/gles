package com.orange.engine.options.resolutionpolicy;

import com.orange.opengl.view.RenderSurfaceView;

/**
 * FixedResolutionPolicy：强行规定游戏画面为固定大小，此设置不会自动适应屏幕大小
 * (c) OrangeGame 2012  
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class FixedResolutionPolicy extends BaseResolutionPolicy {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final int mWidth;
	private final int mHeight;

	// ===========================================================
	// Constructors
	// ===========================================================

	public FixedResolutionPolicy(final int pWidth, final int pHeight) {
		this.mWidth = pWidth;
		this.mHeight = pHeight;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onMeasure(final RenderSurfaceView pRenderSurfaceView, final int pWidthMeasureSpec, final int pHeightMeasureSpec) {
		pRenderSurfaceView.setMeasuredDimensionProxy(this.mWidth, this.mHeight);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
