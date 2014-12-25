package com.orange.util.adt.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * (c) OrangeGame 2012 
 * 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class MultiKeyHashMap<K, V> extends HashMap<MultiKey<K>, V> {
	// ===========================================================
	// Constants
	// ==========================================================

	private static final long serialVersionUID = -6262447639526561122L;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public V get(final K ... pKeys) {
		final int hashCode = MultiKey.hash(pKeys);

		final Iterator<Map.Entry<MultiKey<K>, V>> it = this.entrySet().iterator();
		while(it.hasNext()) {
			final Map.Entry<MultiKey<K>, V> entry = it.next();
			final MultiKey<K> entryKey = entry.getKey();
			if (entryKey.hashCode() == hashCode && this.isEqualKey(entryKey.getKeys(), pKeys)) {
				return entry.getValue();
			}
		}
		return null;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	private boolean isEqualKey(final K[] pKeysA, final K[] pKeysB) {
		if (pKeysA.length != pKeysB.length) {
			return false;
		} else {
			for (int i = 0; i < pKeysA.length; i++) {
				final K keyA = pKeysA[i];
				final K keyB = pKeysB[i];
				if(keyA == null) {
					if(keyB != null) {
						return false;
					}
				} else {
					if(!keyA.equals(keyB)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
