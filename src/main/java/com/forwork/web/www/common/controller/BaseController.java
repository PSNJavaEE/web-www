package com.forwork.web.www.common.controller;

import com.forwork.web.www.common.Exception.ValidateException;
import com.forwork.web.www.common.entity.BizCode;
import com.forwork.web.www.common.entity.ClientReturn;
import com.forwork.web.www.common.entity.PageVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseController {
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 创建ReturnBuilder
	 * 
	 * @return
	 */
	protected ZkwtReturnBuilder createReturnBuilder() {
		return ZkwtReturnBuilder.create();
	}

	/**
	 * 创建ReturnBuilder
	 * 
	 * @return
	 */
	protected ZkwtReturnBuilder createReturnBuilder(Object content) {
		return ZkwtReturnBuilder.create().addContent(content);
	}

	/**
	 * 创建ReturnBuilder
	 * 
	 * @return
	 */
	protected ZkwtReturnBuilder createReturn() {
		return createReturnBuilder();
	}

	/**
	 * 创建ReturnBuilder
	 * 
	 * @return
	 */
	protected ZkwtReturnBuilder createReturn(Object content) {
		return createReturnBuilder().addContent(content);
	}

	/**
	 * 创建ReturnBuilder
	 * 
	 * @return
	 */
	protected ZkwtReturnBuilder getReturnBuilder() {
		return ZkwtReturnBuilder.create();
	}

	/**
	 * 创建ReturnBuilder
	 * 
	 * @return
	 */
	protected ZkwtReturnBuilder getReturnBuilder(Object content) {
		return ZkwtReturnBuilder.create().addContent(content);
	}

	/**
	 * 创建ReturnBuilder
	 * 
	 * @return
	 */
	protected ZkwtReturnBuilder getReturn() {
		return createReturnBuilder();
	}

	/**
	 * 创建ReturnBuilder
	 * 
	 * @return
	 */
	protected ZkwtReturnBuilder getReturn(Object content) {
		return createReturnBuilder().addContent(content);
	}

	/**
	 * 创建ReturnBuilder
	 * 
	 * @return
	 */
	protected ZkwtReturnBuilder successReturn() {
		return successReturn(null);
	}

	/**
	 * 创建ReturnBuilder
	 * 
	 * @param content
	 * @return
	 */
	protected ZkwtReturnBuilder successReturn(Object content) {
		return createReturnBuilder().addSuccess(true).addContent(content);
	}

	protected void successResponse(HttpServletResponse response) {
		successResponse(response, null, null, null);
	}

	protected void successResponse(HttpServletResponse response, Object content) {
		successResponse(response, content, BizCode.SUCCESS, null);
}

	public void successResponse(HttpServletResponse response, BizCode bizCode) {
		successResponse(response, null, bizCode, null);
	}

	protected void successResponse(HttpServletResponse response, String msg) {
		successResponse(response, null, BizCode.SUCCESS, msg);
	}

	protected void successResponse(HttpServletResponse response, Object content, String msg) {
		successResponse(response, content, BizCode.SUCCESS, msg);
	}

	public void successResponse(HttpServletResponse response, Object content, BizCode bizCode) {
		successResponse(response, content, bizCode, null);
	}

	public void successResponse(HttpServletResponse response, Object content, BizCode bizCode, String msg) {
		if (null == bizCode) bizCode = BizCode.SUCCESS;
		if (StringUtils.isEmpty(msg)) msg = bizCode.getMsg();

		try {
			successReturn(content).addCode(bizCode.getCode()).addMsg(msg).responseJSON(response);
		} catch (IOException e) {
			logger.error("响应客户端请求抛出网络异常!");
		}
	}

	/**
	 * 创建ReturnBuilder
	 * 
	 * @return
	 */
	protected ZkwtReturnBuilder falseReturn() {
		return falseReturn(null);
	}

	/**
	 * 创建ReturnBuilder
	 * 
	 * @return
	 */
	protected ZkwtReturnBuilder falseReturn(Object content) {
		return createReturnBuilder().addSuccess(false).addContent(content);
	}

	/**
	 * 创建ReturnBuilder
	 * 
	 * @return
	 */
	protected ZkwtReturnBuilder validateReturn(ValidateException e) {
		return createReturnBuilder().addSuccess(false).addMsg(e.getMsgText()).addCheckMsgs(e.getAllMsgText());
	}

	/**
	 * 创建ReturnBuilder
	 * 
	 * @return
	 */
	protected ZkwtReturnBuilder errorReturn(Exception e) {
		logger.error("Controller异常", e);
		return createReturnBuilder().addSuccess(false).addMsg("系统繁忙，请稍后再试！");
	}

	/**
	 * 将对象格式化为JSON串返回
	 *
	 * @param
	 * @param response
	 * @throws IOException
	 */
	protected void responseJSON(HttpServletResponse response, Object objct) throws IOException {
		this.successReturn(objct).responseJSON(response);
	}

	/**
	 * 将request转化为Map,如果name存在多值情况下，多值将已逗号间隔拼装为字符串
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Map<String, String> request2Map(HttpServletRequest request) {
		Enumeration<String> enumeration = request.getParameterNames();
		Map<String, String> map = new HashMap<String, String>();
		while (enumeration.hasMoreElements()) {
			String name = enumeration.nextElement();
			String[] attri = request.getParameterValues(name);
			if (null != attri && 0 != attri.length) {
				map.put(name, StringUtils.join(attri, ","));
			}
		}
		return map;
	}

	/**
	 * 将request转化为Map,多值将已逗号间隔拼装为字符串
	 * 
	 * @param request
	 * @return
	 */
	protected Map<String, String> toMap(HttpServletRequest request) {
		return request2Map(request);
	}

	/**
	 * 控制层逻辑异常处理
	 * 
	 * @param
	 * @param response
	 * @throws IOException
	 */
	protected void normalContorl(ControlHandler handler, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			handler.execute(request, response);
		} catch (ValidateException e) {
			this.validateReturn(e).responseJSON(response);
		} catch (Exception e) {
			this.errorReturn(e).responseJSON(response);
		}
		// 拓展其它的异常
	}

	/**
	 * 构造PageVO
	 * 
	 * @param rowStart
	 *            数据查询起始行
	 * @param pagePerRow
	 *            每页数据条数
	 * @param total
	 *            总条数
	 * @param list
	 *            数据集
	 * @return
	 */
	protected <E> PageVO newPageVO(int rowStart, int pagePerRow, int total, List<E> list) {
		return new PageVO(rowStart, pagePerRow, total, list);
	}

	/**
	 * 构造PageVO
	 * 
	 * @param rowStart
	 *            数据查询起始行
	 * @param pagePerRow
	 *            每页数据条数
	 * @param total
	 *            总条数
	 * @param list
	 *            数据集
	 * @return
	 */
	protected <E> PageVO newPageVO(String rowStart, String pagePerRow, String total, List<E> list) {
		return new PageVO(Integer.valueOf(rowStart), Integer.valueOf(pagePerRow), Integer.valueOf(total), list);
	}

	/**
	 * 中科梧桐返回类型报表格式
	 * @param obj
	 * @return
	 */
	public ClientReturn forworkReturn(Object obj){
		ClientReturn clientReturn = new ClientReturn();
		clientReturn.setCode("200");
		clientReturn.setContent(obj);
		clientReturn.setMsg("OK");
		clientReturn.setSuccess(true);
		return clientReturn;
	}

	/**
	 * 中科梧桐返回类型报表格式
	 * @param obj
	 * @return
	 */
	public ClientReturn forworkErrorReturn(Object obj,String msg){
		ClientReturn clientReturn = new ClientReturn();
		clientReturn.setContent(obj);
		clientReturn.setMsg(msg);
		clientReturn.setSuccess(false);
		return clientReturn;
	}
}