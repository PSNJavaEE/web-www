package com.forwork.web.www.common.entity;

import org.springframework.context.MessageSource;

import java.util.Locale;

public class BizCode {
	protected static final MessageSource MESSAGES;
	static {
		try {
			MESSAGES = SpringContextUtils.getBean("messageSource", MessageSource.class);
			if (null == MESSAGES) throw new RuntimeException("尚未初始化");
		} catch (Exception e) {
			throw new RuntimeException("MessageSource尚未初始化/初始化失败", e);
		}
	}

	public static final BizCode P_414 = getInstance("414");
	public static final BizCode SUCCESS = getInstance("200");
	public static final BizCode ERROR = getInstance("-1");
	public static final BizCode ERROR_001 = getInstance("-001");
	public static final BizCode WARN_999 = getInstance("-999");

	public static BizCode getInstance(String code, Object... params) {
		return getInstance(code, Locale.CHINESE, params);
	}

	public static BizCode getInstance(String code, Locale locale, Object... params) {
		return new BizCode(code, MESSAGES.getMessage(code, params, locale));
	}

	private String code;
	private String msg;

	protected BizCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}