package com.orange.opengl.font;

import com.orange.opengl.font.exception.LetterNotFoundException;
import com.orange.opengl.texture.ITexture;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IFont {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void load();
	public void unload();

	public ITexture getTexture();

	public float getLineHeight();

	public Letter getLetter(final char pChar) throws LetterNotFoundException;
}
