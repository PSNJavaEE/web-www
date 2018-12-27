package com.forwork.web.www.modules.post.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author yanqunyou
 * @DateTime 2018/12/9 14:22
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors
public class Post {
    private long id;
    private String postName;
    private long deptId;
    private String deptName;
    private String recruitNum;
    private String postSalary;
    private String reqEdu;
    private String reqExp;
    private String address;
    private String reqPost;
    private String postDesc;
    /* 状态：0-草稿(默认)；1-已发布；2-已下线；3-已删除 */
    private String recordState;
    private Date updateTime;
    private Date createTime;

    private String updateTimeFmt;
}