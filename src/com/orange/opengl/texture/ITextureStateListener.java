package com.orange.opengl.texture;


/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface ITextureStateListener {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void onLoadedToHardware(final ITexture pTexture);
	public void onUnloadedFromHardware(final ITexture pTexture);
}