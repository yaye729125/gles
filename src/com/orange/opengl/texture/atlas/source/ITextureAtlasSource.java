package com.orange.opengl.texture.atlas.source;


/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface ITextureAtlasSource {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public int getTextureX();
	public int getTextureY();
	public void setTextureX(final int pTextureX);
	public void setTextureY(final int pTextureY);

	public int getTextureWidth();
	public int getTextureHeight();
	public void setTextureWidth(final int pTextureWidth);
	public void setTextureHeight(final int pTextureHeight);

	public ITextureAtlasSource deepCopy();
}