package com.orange.util.adt.data.operator;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public enum IntOperator {
	// ===========================================================
	// Elements
	// ===========================================================

	EQUALS() {
		@Override
		public boolean check(final int pIntA, final int pIntB) {
			return pIntA == pIntB;
		}
	},
	NOT_EQUALS()  {
		@Override
		public boolean check(final int pIntA, final int pIntB) {
			return pIntA != pIntB;
		}
	},
	LESS_THAN()  {
		@Override
		public boolean check(final int pIntA, final int pIntB) {
			return pIntA < pIntB;
		}
	},
	LESS_OR_EQUAL_THAN()  {
		@Override
		public boolean check(final int pIntA, final int pIntB) {
			return pIntA <= pIntB;
		}
	},
	MORE_THAN()  {
		@Override
		public boolean check(final int pIntA, final int pIntB) {
			return pIntA > pIntB;
		}
	},
	MORE_OR_EQUAL_THAN()  {
		@Override
		public boolean check(final int pIntA, final int pIntB) {
			return pIntA >= pIntB;
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

	public abstract boolean check(final int pIntA, final int pIntB);

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}