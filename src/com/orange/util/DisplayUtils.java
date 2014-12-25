package com.orange.util;

import android.content.Context;
import android.util.DisplayMetrics;

public class DisplayUtils {

	/**
	 * 数组长度为2, int[0]为宽度, int[1]为高度
	 * @param pContext
	 * @return
	 */
	public static int[] getScreenSize(Context pContext){
		DisplayMetrics displayMetrics = pContext.getResources().getDisplayMetrics();
		int displayWidth = displayMetrics.widthPixels;
		int displayHeight = displayMetrics.heightPixels;
		return new int[]{displayWidth, displayHeight};
	}
	
}
