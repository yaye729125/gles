package com.orange.util;

import com.orange.util.time.TimeConstants;

/**
 * (c) OrangeGame 2012 
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public final class TimeUtils implements TimeConstants {
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

	// ===========================================================
	// Methods
	// ===========================================================

	public static final String formatSeconds(final int pSecondsTotal) {
		return formatSeconds(pSecondsTotal, new StringBuilder());
	}

	public static final String formatSeconds(final int pSecondsTotal, final StringBuilder pStringBuilder) {
		final int minutes = pSecondsTotal / SECONDS_PER_MINUTE;
		final int seconds = pSecondsTotal % SECONDS_PER_MINUTE;

		pStringBuilder.append(minutes);
		pStringBuilder.append(':');

		if (seconds < 10) {
			pStringBuilder.append('0');
		}
		pStringBuilder.append(seconds);

		return pStringBuilder.toString();
	}

	/**
	 * 把毫秒格式化成 *h*m*s
	 * 
	 * @param time
	 * @return
	 */
	public static String formMillisecond(long time) {
		long hours = (time % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		long minutes = (time % (1000 * 60 * 60)) / (1000 * 60);
		long seconds = (time % (1000 * 60)) / 1000;
		StringBuffer dateBuffer = new StringBuffer();
		if (hours != 0) {
			dateBuffer.append(Long.toString(hours));
			dateBuffer.append("h");
		}
		if (minutes != 0) {
			dateBuffer.append(Long.toString(minutes));
			dateBuffer.append("m");
		}
		if (seconds != 0) {
			dateBuffer.append(Long.toString(seconds));
			dateBuffer.append("s");
		}
		return dateBuffer.toString();
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
