package com.forwork.web.www.modules.collection.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors
public class Person implements Serializable {
    private Long id;
    private String userName;
    private int age;
    private String sex;
    private String moible;
    private String email;
    private String university;
    private String highestEdu;
    private String profession;
    private String attachUrl;
    private String hopeIndustry;
    private String hopePost;
    private String workYears;
    private Date createTime;
    private Date updateTime;
    private String recordState;
}
