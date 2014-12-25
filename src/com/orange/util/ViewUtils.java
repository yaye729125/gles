package com.orange.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public final class ViewUtils {
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

	// ===========================================================
	// Methods
	// ===========================================================

	public static final View inflate(final Context pContext, final int pLayoutID){
		return LayoutInflater.from(pContext).inflate(pLayoutID, null);
	}

	public static final View inflate(final Context pContext, final int pLayoutID, final ViewGroup pViewGroup){
		return LayoutInflater.from(pContext).inflate(pLayoutID, pViewGroup, true);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
