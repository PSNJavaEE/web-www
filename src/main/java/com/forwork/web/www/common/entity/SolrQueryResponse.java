package com.forwork.web.www.common.entity;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SOLR查询封装对象，通过该对象可执行条件查询、分页、排序功能
 * 
 * @author Song
 * 
 */
public class SolrQueryResponse implements java.io.Serializable, Cloneable {

	private static final long serialVersionUID = -6428794253765151623L;

	/**
	 * 默认页面展示数据条数
	 */
	public static final int DEF_PAGE_SIZE = 10;

	/**
	 * 单页面最大展示数据条数
	 */
	public static final int UNLIMITED_PAGE_SIZE = 100;

	/**
	 * 响应结果状态常量
	 * 
	 * @author Song
	 * 
	 */
	public static class RESP {

		/**
		 * 处理成功
		 */
		public static final String SUCC = "00";

		/**
		 * 处理失败
		 */
		public static final String ERROR_SYS = "99";
	}

	/**
	 * 将要查询的页码
	 */
	private int curPage = 1;

	/**
	 * 页面展示条数
	 */
	private int pageSize = 10;

	/**
	 * 总页数，查询成功后会封装该数据
	 */
	private int totalPage;

	/**
	 * 查询到的总数据量，查询成功后会封装该数据
	 */
	private int size;

	/**
	 * 具体查询结果集对象
	 */
	private List<JSONObject> result;

	/**
	 * 设置查询条件语句
	 */
	private String queryStr;

	/**
	 * 过滤查询，针对queryStr查询结果再次进行过滤
	 */
	private String filterQueryStr;

	/**
	 * 设置返回字段集
	 */
	private List<String> fields;

	/**
	 * 响应状态
	 */
	private String respCode = RESP.ERROR_SYS;

	/**
	 * 是否开启高亮，默认取值为false，表示不开启，开启高亮以后需要设置以下内容：<br/>
	 * <li>设置文档主键字段名称：uniqueFieldName</li> <li>
	 * 设置展示高亮字段名称，该字段应当是经过分词处理后的类型：highlightFieldsName</li> <li>
	 * 设置高亮前缀：simplePre和后缀：simplePost</li>。高亮前缀默认值为&lt;span
	 * class="col_green"&gt;；高亮后缀默认值为&lt;/span&gt;
	 */
	private boolean openHL = false;

	/**
	 * 高亮前缀，默认值为&lt;span class="col_green"&gt;
	 */
	private String simplePre = "<span class=\"col_green\">";

	/**
	 * 高亮后缀，默认值为&lt;/span&gt;
	 */
	private String simplePost = "</span>";

	/**
	 * 主键字段名称
	 */
	private String uniqueFieldName;

	/**
	 * 高亮处理字段名称
	 */
	private List<String> highlightFieldsName;

	/**
	 * 是否使用geo查询，使用geo查询时需要将此值设置为true，同时设置geoProperties查询参数
	 */
	private boolean useGeo = false;

	/**
	 * geo查询参数<br/>
	 * 需要设置以下参数：<br/>
	 * d : 查询半径，单位公里<br/>
	 * sfield：待检索的坐标字段，目前使用的是solr.GeoHashField类型，存储数据使用百度地图坐标“lat,lng”<br/>
	 * pt：检索中心点坐标，仍然需要使用百度地图中的点坐标数据“lat，lng”<br/>
	 */
	private Map<String, String> geoProperties = new HashMap<String, String>();

	/**
	 * 是否使用group查询，使用group查询时需要将此值设置为true，同时设置groupProperties查询参数
	 */
	private boolean useGroup = false;

	/**
	 * 需参考：https://wiki.apache.org/solr/FieldCollapsing 进行合理化设置
	 */
	private Map<String, String> groupProperties = new HashMap<String, String>();

	/**
	 * 有效的查询条件
	 */
	private Map<String, String> queryProps;

	/**
	 * 拼接过滤查询内容
	 * 
	 * @param fqStr
	 *            拼接过滤的查询的字符串内容
	 */
	public void appendFilterQueryStr(String fqStr) {
		this.filterQueryStr = this.filterQueryStr == null ? "" : this.filterQueryStr;
		this.filterQueryStr += fqStr;
	}

	/**
	 * 拼接查询内容
	 * 
	 * @param queryStr
	 *            拼接查询的字符串内容
	 */
	public void appendQueryStr(String queryStr) {
		this.queryStr = this.queryStr == null ? "" : this.queryStr;
		this.queryStr += queryStr;
	}

	/**
	 * 添加需要展示的字段
	 * 
	 * @param fieldName
	 */
	public void addFields(String fieldName) {
		if (null == fields) {
			fields = new ArrayList<String>();
		}
		fields.add(fieldName);
	}

	public List<String> getFields() {
		return fields;
	}

	public String getFilterQueryStr() {
		return filterQueryStr;
	}

	/**
	 * 设置过滤查询的字符串
	 * 
	 * @param filterQueryStr
	 *            过滤查询的字符串内容
	 */
	public void setFilterQueryStr(String filterQueryStr) {
		this.filterQueryStr = filterQueryStr;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
	}

	/**
	 * 获取查询条件语句，如果没有指定，则返回*:*，为执行查询全部
	 * 
	 * @return
	 */
	public String getDefQueryStr() {
		return (null == queryStr || "".equals(queryStr.trim())) ? "*:*" : queryStr;
	}

	public int getCurPage() {
		return curPage;
	}

	/**
	 * 设置查询的页码
	 * 
	 * @param curPage
	 *            查询的页码
	 */
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置查询页面的数据条数
	 * 
	 * @param pageSize
	 *            单页面数据条数
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * 设置获取到的总页数
	 * 
	 * @param totalPage
	 *            总页数
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getSize() {
		return size;
	}

	/**
	 * 设置满足该条件的总数据量
	 * 
	 * @param size
	 *            总数据量
	 */
	public void setSize(int size) {
		this.size = size;
	}

	public List<JSONObject> getResult() {
        return null != result ? result : new ArrayList<JSONObject>();
	}

	/**
	 * 设置查询到的结果集数据对象
	 * 
	 * @param result
	 *            结果集数据对象
	 */
	public void setResult(List<JSONObject> result) {
		this.result = result;
	}

	/**
	 * 添加结果集数据对象
	 * 
	 * @param doc
	 *            数据对象，类型为JSONObject
	 */
	public void addResult(JSONObject doc) {
		if (null == this.result) {
			result = new ArrayList<JSONObject>();
		}
		result.add(doc);
	}

	public String getQueryStr() {
		return queryStr;
	}

	/**
	 * 设置查询条件
	 * 
	 * @param queryStr
	 *            查询条件内容
	 */
	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	public String getRespCode() {
		return respCode;
	}

	/**
	 * 设置响应状态码
	 * 
	 * @param respCode
	 *            状态吗
	 */
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public int getTotal() {
		return this.size;
	}

	public boolean isOpenHL() {
		return openHL;
	}

	public void setOpenHL(boolean openHL) {
		this.openHL = openHL;
	}

	public String getSimplePre() {
		return simplePre;
	}

	public void setSimplePre(String simplePre) {
		this.simplePre = simplePre;
	}

	public String getSimplePost() {
		return simplePost;
	}

	public void setSimplePost(String simplePost) {
		this.simplePost = simplePost;
	}

	public String getUniqueFieldName() {
		return uniqueFieldName;
	}

	public void setUniqueFieldName(String uniqueFieldName) {
		this.uniqueFieldName = uniqueFieldName;
	}

	public List<String> getHighlightFieldsName() {
		return highlightFieldsName;
	}

	public void setHighlightFieldsName(List<String> highlightFieldsName) {
		this.highlightFieldsName = highlightFieldsName;
	}

	public void addHighlightFieldName(String highlightFieldName) {
		if (this.highlightFieldsName == null) {
			this.highlightFieldsName = new ArrayList<String>();
		}
		this.highlightFieldsName.add(highlightFieldName);
	}

	public boolean isUseGeo() {
		return useGeo;
	}

	public void setUseGeo(boolean useGeo) {
		this.useGeo = useGeo;
	}

	public Map<String, String> getGeoProperties() {
		return geoProperties;
	}

	public void setGeoProperties(Map<String, String> geoProperties) {
		this.geoProperties = geoProperties;
	}

	public SolrQueryResponse addGeoProperties(String key, String value) {
		this.getGeoProperties().put(key, value);
		return this;
	}

	public boolean isUseGroup() {
		return useGroup;
	}

	public void setUseGroup(boolean useGroup) {
		this.useGroup = useGroup;
	}

	public Map<String, String> getGroupProperties() {
		return groupProperties;
	}

	public void setGroupProperties(Map<String, String> groupProperties) {
		this.groupProperties = groupProperties;
	}

	public SolrQueryResponse addGroupProperties(String key, String value) {
		this.getGroupProperties().put(key, value);
		return this;
	}

	public Map<String, String> getQueryProps() {
		return queryProps;
	}

	public void setQueryProps(Map<String, String> queryProps) {
		this.queryProps = queryProps;
	}

	public SolrQueryResponse addQueryProps(String key, String value) {
		if (queryProps == null) {
			queryProps = new HashMap<String, String>();
		}
		queryProps.put(key, value);
		return this;
	}

	@Override
	public String toString() {
		return null;
	}

	@Override
	protected SolrQueryResponse clone() throws CloneNotSupportedException {
		return (SolrQueryResponse) super.clone();
	}

}