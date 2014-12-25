package com.orange.entity.particle.initializer;

import com.orange.entity.IEntity;
import com.orange.entity.particle.Particle;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IParticleInitializer<T extends IEntity> {
	// ===========================================================
	// Final Fields
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void onInitializeParticle(final Particle<T> pParticle);
}
