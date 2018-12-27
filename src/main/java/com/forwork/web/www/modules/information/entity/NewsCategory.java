package com.forwork.web.www.modules.information.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors
public class NewsCategory implements Serializable {

	private long id;
	private String name;
	private String image;
	private String description;
	private Date createTime;
	private Date updateTime;
	private int state;

}
