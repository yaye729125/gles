package com.orange.util.adt.data.operator;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public enum DoubleOperator {
	// ===========================================================
	// Elements
	// ===========================================================

	EQUALS() {
		@Override
		public boolean check(final double pDoubleA, final double pDoubleB) {
			return pDoubleA == pDoubleB;
		}
	},
	NOT_EQUALS()  {
		@Override
		public boolean check(final double pDoubleA, final double pDoubleB) {
			return pDoubleA != pDoubleB;
		}
	},
	LESS_THAN()  {
		@Override
		public boolean check(final double pDoubleA, final double pDoubleB) {
			return pDoubleA < pDoubleB;
		}
	},
	LESS_OR_EQUAL_THAN()  {
		@Override
		public boolean check(final double pDoubleA, final double pDoubleB) {
			return pDoubleA <= pDoubleB;
		}
	},
	MORE_THAN()  {
		@Override
		public boolean check(final double pDoubleA, final double pDoubleB) {
			return pDoubleA > pDoubleB;
		}
	},
	MORE_OR_EQUAL_THAN()  {
		@Override
		public boolean check(final double pDoubleA, final double pDoubleB) {
			return pDoubleA >= pDoubleB;
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

	public abstract boolean check(final double pDoubleA, final double pDoubleB);

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}