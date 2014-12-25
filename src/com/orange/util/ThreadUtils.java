package com.orange.util;

import com.orange.util.debug.Debug;
import com.orange.util.debug.Debug.DebugLevel;

/**
 * (c) OrangeGame 2012
 *
 * @author OrangeGame <OGEngine@orangegame.cn>
 */
public class ThreadUtils {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final int STACKTRACE_CALLER_DEPTH = 3;

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

	public static void dumpCurrentThreadInfo() {
		ThreadUtils.dumpCurrentThreadInfo(DebugLevel.DEBUG, Thread.currentThread().getStackTrace()[ThreadUtils.STACKTRACE_CALLER_DEPTH]);
	}

	public static void dumpCurrentThreadInfo(final DebugLevel pDebugLevel) {
		ThreadUtils.dumpCurrentThreadInfo(pDebugLevel, Thread.currentThread().getStackTrace()[ThreadUtils.STACKTRACE_CALLER_DEPTH]);
	}

	private static void dumpCurrentThreadInfo(final DebugLevel pDebugLevel, final StackTraceElement pCaller) {
		Debug.log(pDebugLevel, pCaller.getClassName() + "." + pCaller.getMethodName() + "(" + pCaller.getFileName() + ".java:" + pCaller.getLineNumber() + ") @(Thread: '" + Thread.currentThread().getName() + "')");
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
