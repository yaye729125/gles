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
public class ScaleParticleModifier<T extends IEntity> extends BaseDoubleValueSpanParticleModifier<T> {
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
	 * 缩放粒子修改器
	 * @pFromTime 开始时间
	 * @pToTime 结束时间
	 * @pFromScale 开始缩放值
	 * @pToScale 结束缩放值 */
	public ScaleParticleModifier(final float pFromTime, final float pToTime, final float pFromScale, final float pToScale) {
		this(pFromTime, pToTime, pFromScale, pToScale, EaseLinear.getInstance());
	}

	public ScaleParticleModifier(final float pFromTime, final float pToTime, final float pFromScale, final float pToScale, final IEaseFunction pEaseFunction) {
		this(pFromTime, pToTime, pFromScale, pToScale, pFromScale, pToScale, pEaseFunction);
	}

	public ScaleParticleModifier(final float pFromTime, final float pToTime, final float pFromScaleX, final float pToScaleX, final float pFromScaleY, final float pToScaleY) {
		this(pFromTime, pToTime, pFromScaleX, pToScaleX, pFromScaleY, pToScaleY, EaseLinear.getInstance());
	}

	public ScaleParticleModifier(final float pFromTime, final float pToTime, final float pFromScaleX, final float pToScaleX, final float pFromScaleY, final float pToScaleY, final IEaseFunction pEaseFunction) {
		super(pFromTime, pToTime, pFromScaleX, pToScaleX, pFromScaleY, pToScaleY, pEaseFunction);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void onSetInitialValues(final Particle<T> pParticle, final float pScaleX, final float pScaleY) {
		pParticle.getEntity().setScale(pScaleX, pScaleY);
	}

	@Override
	protected void onSetValues(final Particle<T> pParticle, final float pPercentageDone, final float pScaleX, final float pScaleY) {
		pParticle.getEntity().setScale(pScaleX, pScaleY);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
