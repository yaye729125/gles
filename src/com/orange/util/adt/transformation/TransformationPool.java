package com.orange.util.adt.transformation;

import com.orange.util.adt.pool.GenericPool;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class TransformationPool {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private static final GenericPool<Transformation> POOL = new GenericPool<Transformation>() {
		@Override
		protected Transformation onAllocatePoolItem() {
			return new Transformation();
		}
	};

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	public static Transformation obtain() {
		return POOL.obtainPoolItem();
	}
	
	public static void recycle(final Transformation pTransformation) {
		pTransformation.setToIdentity();
		POOL.recyclePoolItem(pTransformation);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}