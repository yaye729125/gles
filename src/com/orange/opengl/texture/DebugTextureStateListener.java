package com.orange.opengl.texture;

import com.orange.BuildConfig;
import com.orange.opengl.texture.atlas.source.ITextureAtlasSource;
import com.orange.util.debug.Debug;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class DebugTextureStateListener<T extends ITextureAtlasSource> implements ITextureStateListener {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onLoadedToHardware(final ITexture pTexture) {
		if(BuildConfig.DEBUG) {
			Debug.d("Texture loaded: " + pTexture.toString());
		}
	}

	@Override
	public void onUnloadedFromHardware(final ITexture pTexture) {
		if(BuildConfig.DEBUG) {
			Debug.d("Texture unloaded: " + pTexture.toString());
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}