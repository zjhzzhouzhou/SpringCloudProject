package com.dyhospital.cloudhis.common.utils;

import java.util.Iterator;
import java.util.Map;

public class MapUtil {

	/**
	 * 将Map的value拼接成字符串 
	 * Map key1,value1 , key2,value2  => value1|value2|
	 * @param map
	 */
	@SuppressWarnings("rawtypes")
	public static String getValueString(Map map) {
		StringBuffer sb = new StringBuffer();
		for (Iterator ite = map.entrySet().iterator(); ite.hasNext();) {
			Map.Entry entry = (Map.Entry) ite.next();
			String value = (String) entry.getValue();
			sb.append(value).append("|");
		}
		return sb.toString();
	}
}
