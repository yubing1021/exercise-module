package com.darben.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-09 12:06
 */
@Getter
@Setter
@ToString
public class UserInfo {

    private String id;
    private String userName;
    private String userMobile;
    private Integer age;
    private String creatBy;
    private Date createTime;
    private String updateBy;
    private String updateTime;
}
