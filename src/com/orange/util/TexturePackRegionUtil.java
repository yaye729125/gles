package com.orange.util;

public class TexturePackRegionUtil {

	/**
	 * 根据资源中解析出来的名称，转换成动画
	 * @param src 从资源中解析出来的名子
	 * @return    可使用的动画
	 */
	public static String getTextureRegionName(String src) {
		src = src.substring(0, src.lastIndexOf("."));
		int lastIndex = src.lastIndexOf("_");
		if (lastIndex <= 0) {
			return src;
		}
		String lastContent = src.substring(lastIndex + 1);
		try {
			Integer.parseInt(lastContent);
			String content = src.substring(0, lastIndex);
			return content;
		} catch (NumberFormatException e) {
			// TODO: handle exception
			return src;
		}
	}
	
}