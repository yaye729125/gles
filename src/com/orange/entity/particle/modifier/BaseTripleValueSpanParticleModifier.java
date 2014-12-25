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
public abstract class BaseTripleValueSpanParticleModifier<T extends IEntity> extends BaseDoubleValueSpanParticleModifier<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private float mFromValueC;
	private float mValueSpanC;

	// ===========================================================
	// Constructors
	// ===========================================================

	public BaseTripleValueSpanParticleModifier(final float pFromTime, final float pToTime, final float pFromValueA, final float pToValueA, final float pFromValueB, final float pToValueB, final float pFromValueC, final float pToValueC) {
		this(pFromTime, pToTime, pFromValueA, pToValueA, pFromValueB, pToValueB, pFromValueC, pToValueC, EaseLinear.getInstance());
	}

	public BaseTripleValueSpanParticleModifier(final float pFromTime, final float pToTime, final float pFromValueA, final float pToValueA, final float pFromValueB, final float pToValueB, final float pFromValueC, final float pToValueC, final IEaseFunction pEaseFunction) {
		super(pFromTime, pToTime, pFromValueA, pToValueA, pFromValueB, pToValueB, pEaseFunction);

		this.mFromValueC = pFromValueC;
		this.mValueSpanC = pToValueC - pFromValueC;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	protected abstract void onSetInitialValues(final Particle<T> pParticle, final float pValueA, final float pValueB, final float pValueC);
	protected abstract void onSetValues(final Particle<T> pParticle, final float pPercentageDone, final float pValueA, final float pValueB, final float pValueC);

	@Override
	public void onSetInitialValues(final Particle<T> pParticle, final float pValueA, final float pValueB) {
		this.onSetInitialValues(pParticle, pValueA, pValueB, this.mFromValueC);
	}

	@Override
	protected void onSetValues(final Particle<T> pParticle, final float pPercentageDone, final float pValueA, final float pValueB) {
		this.onSetValues(pParticle, pPercentageDone, pValueA, pValueB, this.mFromValueC + pPercentageDone * this.mValueSpanC);
	}

	@Override
	@Deprecated
	public void reset(float pFromValueA, float pToValueA, float pFromValueB, float pToValueB, float pFromTime, float pToTime) {
		super.reset(pFromValueA, pToValueA, pFromValueB, pToValueB, pFromTime, pToTime);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void reset(final float pFromValueA, final float pToValueA, final float pFromValueB, final float pToValueB, final float pFromValueC, final float pToValueC, final float pFromTime, final float pToTime) {
		super.reset(pFromValueA, pToValueA, pFromValueB, pToValueB, pFromTime, pToTime);

		this.mFromValueC = pFromValueC;
		this.mValueSpanC = pToValueC - pFromValueC;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
