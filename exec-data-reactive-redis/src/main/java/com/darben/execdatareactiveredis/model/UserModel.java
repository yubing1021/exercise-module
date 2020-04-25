package com.darben.execdatareactiveredis.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description: 用户信息实体类
 * @author: darben
 * @create: 2020-03-13 16:22
 */
@Setter
@Getter
@ToString
public class UserModel implements Serializable {

    private Integer id;
    private String name;
    private Integer age;
    private String sex;

}
