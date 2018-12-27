package com.forwork.web.www.common.entity;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.forwork.web.www.common.handler.ValueHandler.defaultString;

public class Pagination {
	private int pageNum;

	private int pageSize;
	private int rowStart;

	private Integer total;
	private Boolean hasNext;

	private List<?> listx;

    public static Pagination buildNeed(HttpServletRequest request) {
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");

        return buildNeed(pageNum, pageSize, 1, 10);
    }

	public static Pagination buildNeed(String pageNumStr, String pageSizeStr) {
		return buildNeed(pageNumStr, pageSizeStr, 1, 10);
	}

	public static Pagination buildNeed(String pageNumStr, String pageSizeStr, int defPageNum, int defPageSize) {
		int pageNum = NumberUtils.toInt(pageNumStr, defPageNum);
		int pageSize = NumberUtils.toInt(pageSizeStr, defPageSize);

		return buildNeed(pageNum, pageSize);
	}

	public static Pagination buildNeed(int pageNum, int pageSize) {
		return buildNeed(pageNum, pageSize, null);
	}

	public static Pagination buildNeed(int pageNum, int pageSize, Integer total) {
		Assert.isTrue(pageNum > 0, "Param pageNum must greater than zero");
		Assert.isTrue(pageSize > 0, "Param pageSize must greater than zero");

		Pagination page = getInstance(pageNum, pageSize, total);

		page.checkSetRowStart();
		if (null != page.total) page.checkSetHastNext();

		return page;
	}

	public Pagination buildPage(PageVO vo) {
		return this.buildPage(vo.getTotal(), vo.getList());
	}

	public Pagination buildPage(SolrQueryResponse vo) {
		return this.buildPage(vo.getTotal(), vo.getResult());
	}

	public Pagination buildPage(Integer total) {
		return this.buildPage(total, null);
	}

	public Pagination buildPage(Integer total, List<?> listx) {
		this.total = total;
		this.listx = listx;

		++this.pageNum;
		this.checkSetRowStart();

		this.checkSetHastNext();

		return this;
	}

	public Pagination buildPage(List<?> listx) {
		this.listx = listx;
		return this;
	}

	public Map<String, String> toServiceLimit() {
		Map<String, String> limitx = new HashMap<String, String>();

		limitx.put("rowStart", defaultString(this.rowStart));
		limitx.put("pagePerRow", defaultString(this.pageSize));

		return limitx;
	}

	private void checkSetRowStart() {
		int prevNum = this.pageNum - 1;
		this.rowStart = prevNum * this.pageSize;
	}

	private void checkSetHastNext() {
		if (this.total <= this.pageSize) {
			this.hasNext = false;
		} else if (this.rowStart >= this.total) {
			this.hasNext = false;
		} else {
			this.hasNext = true;
		}
	}

	private static Pagination getInstance(int pageNum, int pageSize, Integer total) {
		return new Pagination(pageNum, pageSize, total);
	}

	private Pagination(int pageNum, int pageSize, Integer total) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.total = total;
	}

	public int getPageNum() {
		return pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public Integer getTotal() {
		return total;
	}

	public int getRowStart() {
		return rowStart;
	}

	public Boolean isHasNext() {
		return hasNext;
	}

    @SuppressWarnings("unchecked")
    public List<JSONObject> getListx() {
        return (List<JSONObject>) listx;
    }

}