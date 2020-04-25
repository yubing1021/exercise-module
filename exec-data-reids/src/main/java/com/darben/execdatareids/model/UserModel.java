package com.darben.execdatareids.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description: 用户信息
 * @author: darben
 * @create: 2020-03-09 15:14
 */
@Getter
@Setter
public class UserModel implements Serializable {

    private Integer id;
    private String name;
    private Integer age;
    private String sex;
}
