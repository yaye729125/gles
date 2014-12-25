package com.orange.entity.particle.modifier;

import com.orange.entity.IEntity;
import com.orange.entity.particle.Particle;
import com.orange.entity.particle.initializer.IParticleInitializer;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public interface IParticleModifier<T extends IEntity> extends IParticleInitializer<T> {
	// ===========================================================
	// Final Fields
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void onUpdateParticle(final Particle<T> pParticle);
}
