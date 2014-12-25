package com.orange.util.adt.data.operator;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public enum FloatOperator {
	// ===========================================================
	// Elements
	// ===========================================================

	EQUALS() {
		@Override
		public boolean check(final float pFloatA, final float pFloatB) {
			return pFloatA == pFloatB;
		}
	},
	NOT_EQUALS()  {
		@Override
		public boolean check(final float pFloatA, final float pFloatB) {
			return pFloatA != pFloatB;
		}
	},
	LESS_THAN()  {
		@Override
		public boolean check(final float pFloatA, final float pFloatB) {
			return pFloatA < pFloatB;
		}
	},
	LESS_OR_EQUAL_THAN()  {
		@Override
		public boolean check(final float pFloatA, final float pFloatB) {
			return pFloatA <= pFloatB;
		}
	},
	MORE_THAN()  {
		@Override
		public boolean check(final float pFloatA, final float pFloatB) {
			return pFloatA > pFloatB;
		}
	},
	MORE_OR_EQUAL_THAN()  {
		@Override
		public boolean check(final float pFloatA, final float pFloatB) {
			return pFloatA >= pFloatB;
		}
	};

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

	public abstract boolean check(final float pFloatA, final float pFloatB);

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}