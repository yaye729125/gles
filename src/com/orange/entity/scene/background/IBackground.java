package com.orange.entity.scene.background;

import com.orange.engine.handler.IDrawHandler;
import com.orange.engine.handler.IUpdateHandler;
import com.orange.util.color.Color;
import com.orange.util.modifier.IModifier;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IBackground extends IDrawHandler, IUpdateHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void registerBackgroundModifier(final IModifier<IBackground> pBackgroundModifier);
	public boolean unregisterBackgroundModifier(final IModifier<IBackground> pBackgroundModifier);
	public void clearBackgroundModifiers();

	public boolean isColorEnabled();
	public void setColorEnabled(final boolean pColorEnabled);

	public void setColor(final Color pColor);
	public void setColor(final float pRed, final float pGreen, final float pBlue);
	public void setColor(final float pRed, final float pGreen, final float pBlue, final float pAlpha);
}
