package com.orange.entity.particle.emitter;

import com.orange.engine.handler.IUpdateHandler;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IParticleEmitter extends IUpdateHandler {
	// ===========================================================
	// Final Fields
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void getPositionOffset(final float[] pOffset);
}
