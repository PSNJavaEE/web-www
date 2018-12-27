package com.forwork.web.www.common.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 */
public class Query extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	// 起始页
	private int pageNum;
	// 每页条数
	private int pageSize;

	public Query(Map<String, Object> params) {
		this.putAll(params);
		// 分页参数
		this.pageNum = Integer.parseInt(params.get("pageNum").toString());
		this.pageSize = Integer.parseInt(params.get("pageSize").toString());
		this.put("pageNum", pageNum);
		this.put("page", pageNum / pageSize + 1);
		this.put("pageSize", pageSize);
	}

	public int getpageNum() {
		return pageNum;
	}

	public void setpageNum(int pageNum) {
		this.put("pageNum", pageNum);
	}

	public int getpageSize() {
		return pageSize;
	}

	public void setpageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
