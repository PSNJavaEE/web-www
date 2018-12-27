package com.forwork.web.www.modules.collection.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author psn
 * @DateTime 2018/12/13
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors
public class Enterprise implements Serializable {
    private Long id;
    private String entpName;
    private String entpSimpleName;
    private String corporation;
    private String entpIndustry;
    private String entpPhone;
    private String entpAddress;
    private String mainBusiness;
    private String personnelType;
    private String personnelEdu;
    private String enclosureUrl;
    private String entpIntro;
    private String recordState;
    private Date createTime;
    private Date updateTime;
}
