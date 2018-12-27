package com.forwork.web.www.common.handler;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ValueHandler {

	public static String defaultString(Object obj) {
		return defaultString(obj, StringUtils.EMPTY);
	}

	public static String defaultString(Object obj, String defaultString) {
		if (null == obj) return defaultString;

		return StringUtils.trimToEmpty(obj.toString());
	}

	public static String handleNullValue(JSONObject tohandle, String field, String defValue) {
		String fieldValue = handleNullValue(tohandle, field);

		return StringUtils.isBlank(fieldValue) ? defValue : fieldValue;
	}

	public static String handleNullValue(JSONObject tohandle, String field) {
		if (null == tohandle || null == field) return StringUtils.EMPTY;

		String fieldValue = tohandle.getString(field);
		fieldValue = null == fieldValue ? StringUtils.EMPTY : fieldValue;

		return fieldValue;
	}

	public static String handleNullArryValue(JSONObject tohandle, String field, String defValue, int index) {
		if (null == tohandle || null == field) return defValue;

		JSONArray fieldValues = tohandle.getJSONArray(field);
		if (null == fieldValues || fieldValues.isEmpty()) return defValue;

		int size = fieldValues.size();
		if (index < 0 || index >= size) index = 0;

		return fieldValues.getString(index);
	}

}