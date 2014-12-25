package com.orange.res;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.res.AssetManager;

import com.orange.opengl.texture.TextureManager;
import com.orange.opengl.texture.TextureOptions;
import com.orange.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import com.orange.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import com.orange.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import com.orange.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import com.orange.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import com.orange.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import com.orange.opengl.texture.atlas.texturepack.TexturePackAtlasTextureRegionFactory;
import com.orange.opengl.texture.region.TextureRegion;
import com.orange.opengl.texture.region.TiledTextureRegion;
import com.orange.util.debug.Debug;

public class RegionRes {

	// ===========================================================
	// 变量
	// ===========================================================
	private final Context mContext;
	private final TextureManager mTextureManager;
	
	private final Map<String, TiledTextureRegion> mTiledTextureRegionMap = new HashMap<String, TiledTextureRegion>();

	// ===========================================================
	// 构造
	// ===========================================================
	
	public RegionRes(Context pContext, TextureManager pTextureManager){
		this.mContext = pContext;
		this.mTextureManager = pTextureManager;
	}
	
	// ===========================================================
	// 方法
	// ===========================================================
	/**
	 * 从文件创建纹理
	 * 
	 * @param mTextureManager
	 * @param pTextureWidth
	 * @param pTextureHeight
	 * @param mTextureRegionName
	 * @param pFilePath
	 */
	public void createFromFile(int pTextureWidth, int pTextureHeight, String pTextureRegionName, String pFilePath) {
		BuildableBitmapTextureAtlas mBitmapTextureAtlas = new BuildableBitmapTextureAtlas(this.mTextureManager, pTextureWidth, pTextureHeight, TextureOptions.NEAREST);
		TextureRegion textureRegion = BitmapTextureAtlasTextureRegionFactory.createFromFile(mBitmapTextureAtlas, pFilePath);
		try {
			mBitmapTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 1));
			mBitmapTextureAtlas.load();
			TiledTextureRegion tiledTextureRegion = TiledTextureRegion.create(mBitmapTextureAtlas, new TextureRegion[] { textureRegion });
			this.mTiledTextureRegionMap.put(pTextureRegionName, tiledTextureRegion);
		} catch (TextureAtlasBuilderException e) {
			Debug.e(e);
		}
	}

	/**
	 * 从字节创建纹理
	 * 
	 * @param mTextureManager
	 * @param pTextureWidth
	 * @param pTextureHeight
	 * @param mTextureRegionName
	 * @param pBytes
	 */
	public void createFromByte(int pTextureWidth, int pTextureHeight, String pTextureRegionName, byte[] pBytes) {
		BuildableBitmapTextureAtlas mBitmapTextureAtlas = new BuildableBitmapTextureAtlas(this.mTextureManager, pTextureWidth, pTextureHeight, TextureOptions.NEAREST);
		TextureRegion textureRegion = BitmapTextureAtlasTextureRegionFactory.createFromByte(mBitmapTextureAtlas, pBytes);
		try {
			mBitmapTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 1));
			mBitmapTextureAtlas.load();
			TiledTextureRegion tiledTextureRegion = TiledTextureRegion.create(mBitmapTextureAtlas, new TextureRegion[] { textureRegion });
			this.mTiledTextureRegionMap.put(pTextureRegionName, tiledTextureRegion);
		} catch (TextureAtlasBuilderException e) {
			Debug.e(e);
		}
	}


	/**
	 * 从Assets创建纹理
	 * 
	 * @param pAssetManager
	 * @param pTextureManager
	 * @param xmlAssetPath
	 */
	public void createFromAssets(String pXmlAssetPath) {
		try {
			Map<String, TiledTextureRegion> tiledTextureRegionMap = TexturePackAtlasTextureRegionFactory.createTiledFromAsset(this.mContext.getAssets(), this.mTextureManager, pXmlAssetPath);
			if (tiledTextureRegionMap != null) {
				this.mTiledTextureRegionMap.putAll(tiledTextureRegionMap);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public TiledTextureRegion getTiledTextureRegion(String pTextureRegionName) {
		return this.mTiledTextureRegionMap.get(pTextureRegionName);
	}


	// ===========================================================
	// 静态方法
	// ===========================================================
	public static void loadTexturesFromFile(int pTextureWidth, int pTextureHeight, String pTextureRegionName, String pFilePath) {
		ResManager.getInstance().getRegionRes().createFromFile(pTextureWidth, pTextureHeight, pTextureRegionName, pFilePath);
	}

	public static void loadTexturesFromByte(int pTextureWidth, int pTextureHeight, String pTextureRegionName, byte[] pBytes) {
		ResManager.getInstance().getRegionRes().createFromByte(pTextureWidth, pTextureHeight, pTextureRegionName, pBytes);
	}
	
	public static void loadTexturesFromAssets(String... pXmlAssetPaths){
		for (String xmlAssetPath : pXmlAssetPaths) {
			loadTexturesFromAssets(xmlAssetPath);
		}
	}
	
	public static void loadTexturesFromAssets(String pXmlAssetPath) {
		ResManager.getInstance().getRegionRes().createFromAssets(pXmlAssetPath);
	}
	// ===========================================================
	// 获取
	// ===========================================================
	
	public static TiledTextureRegion getRegion(String pTextureRegionName) {
		return ResManager.getInstance().getRegionRes().getTiledTextureRegion(pTextureRegionName);
	}
	
	/**
	 * 获取大小，返回长度为2的数组，[0]为宽，[1]为高
	 * @param pTextureRegionName
	 * @return
	 */
	public static float[] getRegionSize(String pTextureRegionName){
		float[] size = new float[2];
		TiledTextureRegion tiledTextureRegion = ResManager.getInstance().getRegionRes().getTiledTextureRegion(pTextureRegionName);
		size[0] = tiledTextureRegion.getWidth();
		size[1] = tiledTextureRegion.getHeight();
		return size;
	}
	
}
