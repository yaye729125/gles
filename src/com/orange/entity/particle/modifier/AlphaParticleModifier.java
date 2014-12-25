package com.orange.entity.particle.modifier;

import com.orange.entity.IEntity;
import com.orange.entity.particle.Particle;
import com.orange.util.modifier.ease.EaseLinear;
import com.orange.util.modifier.ease.IEaseFunction;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class AlphaParticleModifier<T extends IEntity> extends BaseSingleValueSpanParticleModifier<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================
	/**
	 * 透明粒子修改器
	 * @pFromTime 开始时间
	 * @pToTime 结束时间
	 * @pFromScale 开始透明值
	 * @pToScale 结束透明值 */
	public AlphaParticleModifier(final float pFromTime, final float pToTime, final float pFromAlpha, final float pToAlpha) {
		this(pFromTime, pToTime, pFromAlpha, pToAlpha, EaseLinear.getInstance());
	}

	public AlphaParticleModifier(final float pFromTime, final float pToTime, final float pFromAlpha, final float pToAlpha, final IEaseFunction pEaseFunction) {
		super(pFromTime, pToTime, pFromAlpha, pToAlpha, pEaseFunction);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void onSetInitialValue(final Particle<T> pParticle, final float pAlpha) {
		pParticle.getEntity().setAlpha(pAlpha);
	}

	@Override
	protected void onSetValue(final Particle<T> pParticle, final float pPercentageDone, final float pAlpha) {
		pParticle.getEntity().setAlpha(pAlpha);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
