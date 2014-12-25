package com.orange.content;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据处理，用于类间和广播间传递数据
 * 
 * @author OrangeGame <OGEngine@orangegame.cn>
 * 
 */
public class DataBundle {

	// ===========================================================
	// 变量
	// ===========================================================
	/**
	 * 数据存储容器
	 */
	protected Map<String, Object> mDatas = new HashMap<String, Object>();
	/**
	 * 数组数据存储容器
	 */
	protected Map<String, Object[]> mArrayDatas = new HashMap<String, Object[]>();

	// ===========================================================
	// 数据的添加获取过程
	// ===========================================================

	/**
	 * 获取数据对象数组
	 * 
	 * @param key
	 * @return
	 */
	public Object[] getObjectArrayExtra(String pKey) {
		return this.mArrayDatas.get(pKey);
	}

	/**
	 * 获取数据对象
	 * 
	 * @param key
	 * @return
	 */
	public Object getObjectExtra(String pKey) {
		return this.mDatas.get(pKey);
	}

	/**
	 * 获取布尔数组
	 * 
	 * @param key
	 * @return
	 */
	public Boolean[] getBooleanArrayExtra(String pKey) {
		Object[] objects = this.getObjectArrayExtra(pKey);
		if (objects != null) {
			return (Boolean[]) objects;
		}
		return null;
	}

	/**
	 * 获取布尔值
	 * 
	 * @param key
	 * @return
	 */
	public boolean getBooleanExtra(String pKey, boolean defaultValue) {
		Object valueBoolean = this.getObjectExtra(pKey);
		if (valueBoolean == null) {
			return defaultValue;
		} else {
			return (Boolean) valueBoolean;
		}
	}

	/**
	 * 获取字符数组
	 * 
	 * @param key
	 * @return
	 */
	public Character[] getCharArrayExtra(String pKey) {
		Object[] objects = this.getObjectArrayExtra(pKey);
		if (objects != null) {
			return (Character[]) objects;
		}
		return null;
	}

	/**
	 * 获取字符
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public char getCharExtra(String pKey, char defaultValue) {
		Object valueCharacter = this.getObjectExtra(pKey);
		if (valueCharacter == null) {
			return defaultValue;
		} else {
			return (Character) valueCharacter;
		}
	}

	/**
	 * 获取字节数组
	 * 
	 * @param key
	 * @return
	 */
	public Byte[] getByteArrayExtra(String pKey) {
		Object[] objects = this.getObjectArrayExtra(pKey);
		if (objects != null) {
			return (Byte[]) objects;
		}
		return null;
	}

	/**
	 * 获取字节
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public byte getByteExtra(String pKey, byte defaultValue) {
		Object valueByte = this.getObjectExtra(pKey);
		if (valueByte == null) {
			return defaultValue;
		} else {
			return (Byte) valueByte;
		}
	}

	/**
	 * 获取双精数组
	 * 
	 * @param key
	 * @return
	 */
	public Double[] getDoubleArrayExtra(String pKey) {
		Object[] objects = this.getObjectArrayExtra(pKey);
		if (objects != null) {
			return (Double[]) objects;
		}
		return null;
	}

	/**
	 * 获取双精
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public double getDoubleExtra(String pKey, double defaultValue) {
		Object valueDouble = this.getObjectExtra(pKey);
		if (valueDouble == null) {
			return defaultValue;
		} else {
			return (Double) valueDouble;
		}
	}

	/**
	 * 获取单精数组
	 * 
	 * @param key
	 * @return
	 */
	public Float[] getFloatArrayExtra(String pKey) {
		Object[] objects = this.getObjectArrayExtra(pKey);
		if (objects != null) {
			return (Float[]) objects;
		}
		return null;
	}

	/**
	 * 获取单精
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public float getFloatExtra(String pKey, float defaultValue) {
		Object valueFloat = this.getObjectExtra(pKey);
		if (valueFloat == null) {
			return defaultValue;
		} else {
			return (Float) valueFloat;
		}
	}

	/**
	 * 获取整数数组
	 * 
	 * @param key
	 * @return
	 */
	public Integer[] getIntArrayExtra(String pKey) {
		Object[] objects = this.getObjectArrayExtra(pKey);
		if (objects != null) {
			return (Integer[]) objects;
		}
		return null;
	}

	/**
	 * 获取整数
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public int getIntExtra(String pKey, int defaultValue) {
		Object valueInteger = this.getObjectExtra(pKey);
		if (valueInteger == null) {
			return defaultValue;
		} else {
			return (Integer) valueInteger;
		}
	}

	/**
	 * 获取长整数组
	 * 
	 * @param key
	 * @return
	 */
	public Long[] getLongArrayExtra(String pKey) {
		Object[] objects = this.getObjectArrayExtra(pKey);
		if (objects != null) {
			return (Long[]) objects;
		}
		return null;
	}

	/**
	 * 获取长整
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public long getLongExtra(String pKey, long defaultValue) {
		Object valueLong = this.getObjectExtra(pKey);
		if (valueLong == null) {
			return defaultValue;
		} else {
			return (Long) valueLong;
		}
	}

	/**
	 * 获取短整数组
	 * 
	 * @param key
	 * @return
	 */
	public Short[] getShortArrayExtra(String pKey) {
		Object[] objects = this.getObjectArrayExtra(pKey);
		if (objects != null) {
			return (Short[]) objects;
		}
		return null;
	}

	/**
	 * 获取短整
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public short getShortExtra(String pKey, short defaultValue) {
		Object valueShort = this.getObjectExtra(pKey);
		if (valueShort == null) {
			return defaultValue;
		} else {
			return (Short) valueShort;
		}
	}

	/**
	 * 获取字符串数组
	 * 
	 * @param key
	 * @return
	 */
	public String[] getStringArrayExtra(String pKey) {
		Object[] objects = this.getObjectArrayExtra(pKey);
		if (objects != null) {
			return (String[]) objects;
		}
		return null;
	}

	/**
	 * 获取字符串
	 * 
	 * @param key
	 * @return
	 */
	public String getStringExtra(String pKey, String defaultValue) {
		Object valueShort = this.getObjectExtra(pKey);
		if (valueShort == null) {
			return defaultValue;
		} else {
			return (String) valueShort;
		}
	}
	
	public String getStringExtra(String pKey) {
		return this.getStringExtra(pKey, null);
	}

	// /////////////////////////////////////////////////////////////////////////////

	public void putObjectArrayExtra(String pKey, Object[] pValues) {
		this.mArrayDatas.put(pKey, pValues);
	}

	/**
	 * 添加数据对象
	 * 
	 * @param key
	 * @param object
	 */
	public void putObjectExtra(String pKey, Object value) {
		this.mDatas.put(pKey, value);
	}

	/**
	 * 添加布尔数组
	 * 
	 * @param key
	 * @return
	 */
	public void putBooleanArrayExtra(String pKey, Boolean[] pValues) {
		this.mArrayDatas.put(pKey, pValues);
	}

	/**
	 * 获取布尔值
	 * 
	 * @param key
	 * @return
	 */
	public void putBooleanExtra(String pKey, boolean value) {
		this.mDatas.put(pKey, value);
	}

	/**
	 * 获取字符数组
	 * 
	 * @param key
	 * @return
	 */
	public void putCharArrayExtra(String pKey, Character[] pValues) {
		this.mArrayDatas.put(pKey, pValues);
	}

	/**
	 * 获取字符
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public void putCharExtra(String pKey, char value) {
		this.mDatas.put(pKey, value);
	}

	/**
	 * 获取字节数组
	 * 
	 * @param key
	 * @return
	 */
	public void putByteArrayExtra(String pKey, Byte[] pValues) {
		this.mArrayDatas.put(pKey, pValues);
	}

	/**
	 * 获取字节
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public void putByteExtra(String pKey, byte value) {
		this.mDatas.put(pKey, value);
	}

	/**
	 * 获取双精数组
	 * 
	 * @param key
	 * @return
	 */
	public void putDoubleArrayExtra(String pKey, Double[] pValues) {
		this.mArrayDatas.put(pKey, pValues);
	}

	/**
	 * 获取双精
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public void putDoubleExtra(String pKey, double value) {
		this.mDatas.put(pKey, value);
	}

	/**
	 * 获取单精数组
	 * 
	 * @param key
	 * @return
	 */
	public void putFloatArrayExtra(String pKey, Float[] pValues) {
		this.mArrayDatas.put(pKey, pValues);
	}

	/**
	 * 获取单精
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public void putFloatExtra(String pKey, float value) {
		this.mDatas.put(pKey, value);
	}

	/**
	 * 获取整数数组
	 * 
	 * @param key
	 * @return
	 */
	public void putIntArrayExtra(String pKey, Integer[] pValues) {
		this.mArrayDatas.put(pKey, pValues);
	}

	/**
	 * 获取整数
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public void putIntExtra(String pKey, int value) {
		this.mDatas.put(pKey, value);
	}

	/**
	 * 获取长整数组
	 * 
	 * @param key
	 * @return
	 */
	public void putLongArrayExtra(String pKey, Long[] pValues) {
		this.mArrayDatas.put(pKey, pValues);
	}

	/**
	 * 获取长整
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public void putLongExtra(String pKey, long value) {
		this.mDatas.put(pKey, value);
	}

	/**
	 * 获取短整数组
	 * 
	 * @param key
	 * @return
	 */
	public void putShortArrayExtra(String pKey, Short[] pValues) {
		this.mArrayDatas.put(pKey, pValues);
	}

	/**
	 * 获取短整
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public void putShortExtra(String pKey, short value) {
		this.mDatas.put(pKey, value);
	}

	/**
	 * 获取字符串数组
	 * 
	 * @param key
	 * @return
	 */
	public void putStringArrayExtra(String pKey, String[] pValues) {
		this.mArrayDatas.put(pKey, pValues);
	}

	/**
	 * 获取字符串
	 * 
	 * @param key
	 * @return
	 */
	public void putStringExtra(String pKey, String value) {
		this.mDatas.put(pKey, value);
	}

	// ===========================================================
	// 特有方法
	// ===========================================================
	/**
	 * 获取数据对象表
	 * 
	 * @return
	 */
	public Map<String, Object> getExtras() {
		return mDatas;
	}

}
