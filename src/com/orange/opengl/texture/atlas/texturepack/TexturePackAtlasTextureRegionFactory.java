package com.orange.opengl.texture.atlas.texturepack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.res.AssetManager;

import com.orange.opengl.texture.ITexture;
import com.orange.opengl.texture.TextureManager;
import com.orange.opengl.texture.region.TextureRegion;
import com.orange.opengl.texture.region.TiledTextureRegion;
import com.orange.ui.activity.GameActivity;
import com.orange.util.TexturePackRegionUtil;
import com.orange.util.debug.Debug;
import com.orange.util.texturepack.TexturePack;
import com.orange.util.texturepack.TexturePackLoader;
import com.orange.util.texturepack.TexturePackTextureRegion;
import com.orange.util.texturepack.TexturePackTextureRegionLibrary;
import com.orange.util.texturepack.exception.TexturePackParseException;

public class TexturePackAtlasTextureRegionFactory {

	public static Map<String, TiledTextureRegion> createTiledFromAsset(final AssetManager pAssetManager, final TextureManager pTextureManager, String xmlAssetPath) throws Exception{
		try {
			// 路径处理过程
			if (xmlAssetPath == null) {
				throw new Exception("资源路径不能为null");
			}
			String pAssetBasePath = "";
			String pAssetPath = xmlAssetPath;
			int lastIndex = xmlAssetPath.lastIndexOf("/");
			if (lastIndex > 0) {
				pAssetBasePath = xmlAssetPath.substring(0, lastIndex + 1);
			}
//			pAssetPath = xmlAssetPath.substring(lastIndex + 1, xmlAssetPath.length());
			//纹理加载读取
			TexturePackLoader texturePackLoader = new TexturePackLoader(pAssetManager, pTextureManager);
			TexturePack spritesheetTexturePack = texturePackLoader.loadFromAsset(pAssetPath, pAssetBasePath);
			spritesheetTexturePack.loadTexture();
			TexturePackTextureRegionLibrary texturePackTextureRegionLibrary = spritesheetTexturePack.getTexturePackTextureRegionLibrary();
			return createTiled(texturePackTextureRegionLibrary);
		} catch (final TexturePackParseException e) {
			Debug.e(e);
			return null;
		}
	}
	
	private static Map<String, TiledTextureRegion> createTiled(TexturePackTextureRegionLibrary texturePackTextureRegionLibrary) throws Exception{
		// 贴图重新排序处理
		HashMap<String,  TexturePackTextureRegion> mSourceMapping = texturePackTextureRegionLibrary.getSourceMapping();
		List<String> srcs = new ArrayList<String>();
		for (String source : mSourceMapping.keySet()) {
			srcs.add(source);
		}
		Collections.sort(srcs);
		
		// 生成动画名称--贴图集合
		Map<String, List<TexturePackTextureRegion>> mTexturePackTextureRegionListMap = new HashMap<String, List<TexturePackTextureRegion>>();
		for (int i = 0; i < srcs.size(); i++) {
			String src = srcs.get(i);
			// 生成动画名称
			String mTextureRegionName = TexturePackRegionUtil.getTextureRegionName(src);

			if (mTexturePackTextureRegionListMap.get(mTextureRegionName) == null) {
				mTexturePackTextureRegionListMap.put(mTextureRegionName, new ArrayList<TexturePackTextureRegion>());
			}
			mTexturePackTextureRegionListMap.get(mTextureRegionName).add(mSourceMapping.get(src));
		}

		//生成TiledTextureRegion集合
		Map<String, TiledTextureRegion> tiledTextureRegionMap = new HashMap<String, TiledTextureRegion>();
		for (String mTextureRegionName : mTexturePackTextureRegionListMap.keySet()) {
			List<TexturePackTextureRegion> mTexturePackTextureRegions = mTexturePackTextureRegionListMap.get(mTextureRegionName);
			if (mTexturePackTextureRegions == null || mTexturePackTextureRegions.size() <= 0) {
				throw new Exception("帧列表不能为null");
			}
			TextureRegion[] textureRegions = new TextureRegion[mTexturePackTextureRegions.size()];
			mTexturePackTextureRegions.toArray(textureRegions);
			ITexture texture = texturePackTextureRegionLibrary.getTexture();
			TiledTextureRegion tiledTextureRegion = TiledTextureRegion.create(texture, textureRegions);
			tiledTextureRegionMap.put(mTextureRegionName, tiledTextureRegion);
		}
		return tiledTextureRegionMap;
	}
	
	
}
