package com.forwork.web.www.modules.information.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors
public class TechPolicy {
    private Long id;
    private String title;
    private String classify;
    private String subClassiify;
    private String originalUrl;
    private String platName;
    private String publishTime;
    private String picUrl;
    private String province;
    private String city;
    private String tags;
    private String content;
    private String istop;
    private int recomnum;
    private String status;
    private Date updateTime;
    private Date addTime;
    /**1：科技政策，2：试点政策*/
    private String policyType;
    /**1：本地内容，2：URL地址*/
    private String contType;

    private LastOne lastOne;
    private NextOne nextOne;

    private String[] tagsList;
}
