package com.orange.res;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Typeface;

import com.orange.opengl.font.Font;
import com.orange.opengl.font.FontFactory;
import com.orange.opengl.font.FontManager;
import com.orange.opengl.texture.TextureManager;
import com.orange.opengl.texture.TextureOptions;
import com.orange.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import com.orange.opengl.texture.bitmap.BitmapTextureFormat;

public class FontRes {

	// ===========================================================
	// 变量
	// ===========================================================
	private final Context mContext;
	private final FontManager mFontManager;
	private final TextureManager mTextureManager;
	
	private Map<String, Font> mFontMap = new HashMap<String, Font>();

	// ===========================================================
	// 构造
	// ===========================================================
	public FontRes(Context pContext, FontManager pFontManager, TextureManager pTextureManager){
		this.mContext = pContext;
		this.mFontManager = pFontManager;
		this.mTextureManager = pTextureManager;
	}
	
	
	public void createFont(int pTextureWidth, int pTextureHeight, Typeface pTypeface, float pSize, boolean pAntiAlias, int pColor, String pFontName) {
		BitmapTextureAtlas bitmapTextureAtlas = new BitmapTextureAtlas(this.mTextureManager, pTextureWidth, pTextureHeight, BitmapTextureFormat.RGBA_8888, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		Font font = FontFactory.create(this.mFontManager, bitmapTextureAtlas, pTypeface, pSize, pAntiAlias,  pColor);
		font.load();
		this.mFontMap.put(pFontName, font);
	}

	public Font getFontForMap(String pFontName) {
		return this.mFontMap.get(pFontName);
	}

	// ===========================================================
	// 静态方法
	// ===========================================================

	public static void loadFont(int pTextureWidth, int pTextureHeight, Typeface pTypeface, float pSize, boolean pAntiAlias, int pColor, String pFontName) {
		ResManager.getInstance().getFontRes().createFont(pTextureWidth, pTextureHeight, pTypeface, pSize, pAntiAlias, pColor, pFontName);
	}
	
	public static Font getFont(String pFontName){
		return ResManager.getInstance().getFontRes().getFontForMap(pFontName);
	}
}
