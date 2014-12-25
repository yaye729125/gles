package com.orange.opengl.font;

import com.orange.util.adt.map.Library;

import android.util.SparseArray;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class FontLibrary extends Library<Font> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public FontLibrary() {
		super();
	}

	public FontLibrary(final int pInitialCapacity) {
		super(pInitialCapacity);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void loadFonts(final FontManager pFontManager) {
		final SparseArray<Font> items = this.mItems;
		for(int i = items.size() - 1; i >= 0; i--) {
			final Font font = items.valueAt(i);
			if(font != null) {
				pFontManager.loadFont(font);
			}
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
