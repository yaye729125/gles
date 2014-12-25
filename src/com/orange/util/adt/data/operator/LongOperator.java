package com.orange.util.adt.data.operator;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public enum LongOperator {
	// ===========================================================
	// Elements
	// ===========================================================

	EQUALS() {
		@Override
		public boolean check(final long pLongA, final long pLongB) {
			return pLongA == pLongB;
		}
	},
	NOT_EQUALS()  {
		@Override
		public boolean check(final long pLongA, final long pLongB) {
			return pLongA != pLongB;
		}
	},
	LESS_THAN()  {
		@Override
		public boolean check(final long pLongA, final long pLongB) {
			return pLongA < pLongB;
		}
	},
	LESS_OR_EQUAL_THAN()  {
		@Override
		public boolean check(final long pLongA, final long pLongB) {
			return pLongA <= pLongB;
		}
	},
	MORE_THAN()  {
		@Override
		public boolean check(final long pLongA, final long pLongB) {
			return pLongA > pLongB;
		}
	},
	MORE_OR_EQUAL_THAN()  {
		@Override
		public boolean check(final long pLongA, final long pLongB) {
			return pLongA >= pLongB;
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

	public abstract boolean check(final long pLongA, final long pLongB);

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}