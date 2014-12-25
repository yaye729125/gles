package com.orange.entity.modifier;

import com.orange.entity.IEntity;
import com.orange.util.modifier.ease.EaseLinear;
import com.orange.util.modifier.ease.IEaseFunction;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class JumpModifier extends MoveModifier {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final int JUMPCOUNT_DEFAULT = 1;

	// ===========================================================
	// Fields
	// ===========================================================

	protected final float mJumpHeight;
	protected final int mJumpCount;

	// ===========================================================
	// Constructors
	// ===========================================================

	public JumpModifier(final float pDuration, final float pFromX, final float pToX, final float pFromY, final float pToY, final float pJumpHeight) {
		this(pDuration, pFromX, pToX, pFromY, pToY, pJumpHeight, JumpModifier.JUMPCOUNT_DEFAULT, EaseLinear.getInstance());
	}

	public JumpModifier(final float pDuration, final float pFromX, final float pToX, final float pFromY, final float pToY, final float pJumpHeight, final IEaseFunction pEaseFunction) {
		this(pDuration, pFromX, pToX, pFromY, pToY, pJumpHeight, JumpModifier.JUMPCOUNT_DEFAULT, pEaseFunction);
	}

	public JumpModifier(final float pDuration, final float pFromX, final float pToX, final float pFromY, final float pToY, final float pJumpHeight, final IEntityModifierListener pEntityModifierListener) {
		this(pDuration, pFromX, pToX, pFromY, pToY, pJumpHeight, JumpModifier.JUMPCOUNT_DEFAULT, pEntityModifierListener, EaseLinear.getInstance());
	}

	public JumpModifier(final float pDuration, final float pFromX, final float pToX, final float pFromY, final float pToY, final float pJumpHeight, final IEntityModifierListener pEntityModifierListener, final IEaseFunction pEaseFunction) {
		this(pDuration, pFromX, pToX, pFromY, pToY, pJumpHeight, JumpModifier.JUMPCOUNT_DEFAULT, pEntityModifierListener, pEaseFunction);
	}

	public JumpModifier(final float pDuration, final float pFromX, final float pToX, final float pFromY, final float pToY, final float pJumpHeight, final int pJumpCount) {
		this(pDuration, pFromX, pToX, pFromY, pToY, pJumpHeight, pJumpCount, EaseLinear.getInstance());
	}

	public JumpModifier(final float pDuration, final float pFromX, final float pToX, final float pFromY, final float pToY, final float pJumpHeight, final int pJumpCount, final IEaseFunction pEaseFunction) {
		this(pDuration, pFromX, pToX, pFromY, pToY, pJumpHeight, pJumpCount, null, pEaseFunction);
	}

	public JumpModifier(final float pDuration, final float pFromX, final float pToX, final float pFromY, final float pToY, final float pJumpHeight, final int pJumpCount, final IEntityModifierListener pEntityModifierListener) {
		this(pDuration, pFromX, pToX, pFromY, pToY, pJumpHeight, pJumpCount, pEntityModifierListener, EaseLinear.getInstance());
	}

	public JumpModifier(final float pDuration, final float pFromX, final float pToX, final float pFromY, final float pToY, final float pJumpHeight, final int pJumpCount, final IEntityModifierListener pEntityModifierListener, final IEaseFunction pEaseFunction) {
		super(pDuration, pFromX, pToX, pFromY, pToY, pEntityModifierListener, pEaseFunction);

		this.mJumpHeight = pJumpHeight;
		this.mJumpCount = pJumpCount;
	}

	public JumpModifier(final JumpModifier pJumpModifier) {
		super(pJumpModifier);

		this.mJumpHeight = pJumpModifier.mJumpHeight;
		this.mJumpCount = pJumpModifier.mJumpCount;
	}

	@Override
	public JumpModifier deepCopy() throws DeepCopyNotSupportedException {
		return new JumpModifier(this);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void onSetValues(final IEntity pEntity, final float pPercentageDone, final float pX, final float pY) {
		final float fraction = (pPercentageDone * this.mJumpCount) % 1.0f;
		final float deltaY = this.mJumpHeight * 4 * fraction * (1 - fraction);

		super.onSetValues(pEntity, pPercentageDone, pX, pY - deltaY);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
