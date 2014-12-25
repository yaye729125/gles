package com.orange.opengl.font;

import java.util.ArrayList;

import com.orange.opengl.util.GLState;


/**
 * (c) OrangeGame 2012
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class FontManager {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final ArrayList<Font> mFontsManaged = new ArrayList<Font>();

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void onCreate() {

	}

	public synchronized void onDestroy() {
		final ArrayList<Font> managedFonts = this.mFontsManaged;
		for(int i = managedFonts.size() - 1; i >= 0; i--) {
			managedFonts.get(i).invalidateLetters();
		}

		this.mFontsManaged.clear();
	}

	public synchronized void loadFont(final Font pFont) {
		if(pFont == null) {
			throw new IllegalArgumentException("pFont must not be null!");
		}
		this.mFontsManaged.add(pFont);
	}

	public synchronized void loadFonts(final Font ...pFonts) {
		for(int i = 0; i < pFonts.length; i++) {
			this.loadFont(pFonts[i]);
		}
	}

	public synchronized void unloadFont(final Font pFont) {
		if(pFont == null) {
			throw new IllegalArgumentException("pFont must not be null!");
		}
		this.mFontsManaged.remove(pFont);
	}

	public synchronized void unloadFonts(final Font ...pFonts) {
		for(int i = 0; i < pFonts.length; i++) {
			this.unloadFont(pFonts[i]);
		}
	}

	public synchronized void updateFonts(final GLState pGLState) {
		final ArrayList<Font> fonts = this.mFontsManaged;
		final int fontCount = fonts.size();
		if(fontCount > 0){
			for(int i = fontCount - 1; i >= 0; i--){
				fonts.get(i).update(pGLState);
			}
		}
	}

	public synchronized void onReload() {
		final ArrayList<Font> managedFonts = this.mFontsManaged;
		for(int i = managedFonts.size() - 1; i >= 0; i--) {
			managedFonts.get(i).invalidateLetters();
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
