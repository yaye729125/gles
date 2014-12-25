package com.orange.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.orange.util.debug.Debug;

/**
 * 反射工具类
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 * 
 */
public class ReflectionUtils {

	/**
	 * 实例化，带参数
	 * 
	 * @param cls
	 * @param parameterTypes
	 *            和 argParam任意一个内容传入null，则使用默认构造方法
	 * @param argParam
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(Class<T> cls, Class<?>[] parameterTypes, Object[] argParam) {
		try {
			Object object = null;
			if (parameterTypes == null || argParam == null) {
				Constructor<T> constructor = cls.getConstructor();
				constructor.setAccessible(true);
				object = constructor.newInstance();
			} else {
				Constructor<T> constructor = cls.getConstructor(parameterTypes);
				constructor.setAccessible(true);
				object = constructor.newInstance(argParam);
			}
			return (T) object;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T newInstance(Class<T> cls) {
		return ReflectionUtils.newInstance(cls, null, null);
	}

	private static Method getMethod(Class<?> cls, String name, Class<?>[] parameterTypes) {
		try {
			return cls.getDeclaredMethod(name, parameterTypes);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			Class<?> superclass = cls.getSuperclass();
			if (superclass != null) {
				return ReflectionUtils.getMethod(superclass, name, parameterTypes);
			}
		}
		return null;
	}

	public static Object invokeMethod(Object obj, boolean isStatic, String name, Class<?>[] parameterTypes, Object[] argParam) {
		try {
			Class<?> cls = obj.getClass();
			Method method = ReflectionUtils.getMethod(cls, name, parameterTypes);
			method.setAccessible(true);
			Object resultObject = null;
			if (isStatic) {
				resultObject = method.invoke(null, argParam);
			} else {
				resultObject = method.invoke(obj, argParam);
			}
			return resultObject;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Object invokeMethod(Object obj, String name, Class<?>[] parameterTypes, Object[] argParam) {
		return ReflectionUtils.invokeMethod(obj, false, name, parameterTypes, argParam);
	}

	public static Object invokeStaticMethod(Object obj, String name, Class<?>[] parameterTypes, Object[] argParam) {
		return ReflectionUtils.invokeMethod(obj, true, name, parameterTypes, argParam);
	}

}
