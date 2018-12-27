package com.forwork.web.www.common.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;

/**
 * <ul>
 * <li><b>目的:</b> <br />
 * <p>
 * </p>
 * </li>
 * <li><b>注意事项：</b></li>
 * <li><b>修改历史：</b><br />
 * <p>
 * 创建: 2014-9-25 上午11:37:59<br />
 * 作者:<a herf="mailto:284654376@qq.com">周志明</a>
 * </p>
 * </li>
 * <li><b>已知问题：</b></li>
 * </ul>
 */
public class ZkwtReturnBuilder {
	ClientReturn client = new ClientReturn();
	ValueFilter filter = new ValueFilter();

	public static ZkwtReturnBuilder create() {
		return new ZkwtReturnBuilder();
	}

	public static ZkwtReturnBuilder instance() {
		return new ZkwtReturnBuilder();
	}

	public static ZkwtReturnBuilder ins() {
		return new ZkwtReturnBuilder();
	}

	/**
	 * 添加method
	 * 
	 * @param method
	 * @return
	 */
	public ZkwtReturnBuilder addMethod(String method) {
		client.setMethod(method);
		return this;
	}

	/**
	 * 添加结果
	 * 
	 * @param success
	 * @return
	 */
	public ZkwtReturnBuilder addSuccess(boolean success) {
		client.setSuccess(success);
		return this;
	}

	/**
	 * 添加编码
	 * 
	 * @param code
	 * @return
	 */
	public ZkwtReturnBuilder addCode(String code) {
		client.setCode(code);
		return this;
	}

	/**
	 * 添加二级编码
	 * 
	 * @param subCode
	 * @return
	 */
	public ZkwtReturnBuilder addSubCode(String subCode) {
		client.setSubCode(subCode);
		return this;
	}

	/**
	 * 添加消息
	 * 
	 * @param msg
	 * @return
	 */
	public ZkwtReturnBuilder addMsg(String msg) {
		client.setMsg(msg);
		return this;
	}

	/**
	 * 添加二级消息
	 * 
	 * @param subMsg
	 * @return
	 */
	public ZkwtReturnBuilder addSubMsg(Object subMsg) {
		client.setSubMsg(subMsg);
		return this;
	}

	/**
	 * 添加校验消息
	 * 
	 * @param checkMsgs
	 * @return
	 */
	public ZkwtReturnBuilder addCheckMsgs(Map<String, String> checkMsgs) {
		client.setCheckMsgs(checkMsgs);
		return this;
	}

	/**
	 * 添加响应内容
	 * 
	 * @param jsonText
	 * @return
	 */
	public ZkwtReturnBuilder addContent(Object content) {
		client.setContent(content);
		return this;
	}

	/**
	 * 转化为JSON串
	 * 
	 * @return
	 */
	public String toJSONString() {
		return JSON.toJSONString(client, filter);
	}

	/**
	 * 将对象格式化为JSON串返回
	 * 
	 * @param response
	 * @param result
	 * @throws IOException
	 */
	public void responseJSON(HttpServletResponse response, String charset) throws IOException {
		String json = this.toJSONString();
		response.setCharacterEncoding(charset);
		response.getWriter().print(json);
	}

	/**
	 * 将对象格式化为JSON串返回
	 * 
	 * @param response
	 * @param result
	 * @throws IOException
	 */
	public void responseJSON(HttpServletResponse response) throws IOException {
		responseJSON(response, "UTF-8");
	}

	class ValueFilter implements com.alibaba.fastjson.serializer.ValueFilter {
		@Override
		public Object process(Object source, String name, Object value) {
			if (source instanceof ClientReturn) {
				return value;
			}

			/**
			 * 此处可据field type进行定制处理,暂无该需求
			 */
			if (null == value) {
				return StringUtils.EMPTY;
			}

			Class<? extends Object> clazz = value.getClass();
			if (!(value instanceof Boolean) && //
					(isPrimitive(clazz) || isWrapClass(clazz))) {
				return value.toString();
			} else {
				if (!(value instanceof Date)) {
					return value;
				} else {
					return String.valueOf(((Date) value).getTime());
				}
			}
		}

		public boolean isPrimitive(Class<?> clazz) {
			return clazz.isPrimitive();
		}

		public boolean isWrapClass(Class<?> clazz) {
			try {
				Field field = clazz.getField("TYPE");
				return ((Class<?>) field.get(null)).isPrimitive();
			} catch (Exception e) {
				return false;
			}
		}
	}

	class ClientReturn {
		private String method = null;
		private boolean success = true;
		private String code = null;
		private String subCode = null;
		private String msg = null;
		private Object subMsg = null;
		private Map<String, String> checkMsgs = null;
		private Object content = null;

		public String getMethod() {
			return method;
		}

		public void setMethod(String method) {
			this.method = method;
		}

		public boolean isSuccess() {
			return success;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getSubCode() {
			return subCode;
		}

		public void setSubCode(String subCode) {
			this.subCode = subCode;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public Object getContent() {
			return content;
		}

		public void setContent(Object content) {
			this.content = content;
		}

		public Object getSubMsg() {
			return subMsg;
		}

		public void setSubMsg(Object subMsg) {
			this.subMsg = subMsg;
		}

		public Map<String, String> getCheckMsgs() {
			return checkMsgs;
		}

		public void setCheckMsgs(Map<String, String> checkMsgs) {
			this.checkMsgs = checkMsgs;
		}

	}

}
