package com.forwork.web.www.modules.information.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author forwork
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors
public class News implements Serializable {

	private long id;
	private String title;
	private String url;
	/**新闻内容类型（1:本地内容，2:url链接其他地址）*/
	private String newsType;
	private long categoryId;
	private String categoryName;
	private String categoryImage;
	private String image;
	/**推荐状态（1:正常,2:推荐）*/
	private int commendState;
	/**显示状态（1：显示，2：隐藏）*/
	private int displayState;
    /**状态（1：正常，2：删除）*/
    private int state;

	private String description;
	private String content;
    /**关键字(多个使用英文逗号隔开)*/
	private String keywords;
	private Date createTime;
	private Date updateTime;

	private int browses;
	private int likes;
	private int comments;

	private LastOne lastOne;
	private NextOne nextOne;

	private String[] keywordsList;

	private String yearMonth;
	private String days;
}
