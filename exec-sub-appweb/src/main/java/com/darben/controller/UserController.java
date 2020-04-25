package com.darben.controller;

import com.darben.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @description: 用户信息
 * @author: darben
 * @create: 2020-04-09 12:05
 */
@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {

    private int scopeAge = 25;

    @RequestMapping(value = "/getUserInfoById")
    public UserInfo getUserInfoById(String id){

        log.info("getUserInfoById id [{}]",id);

        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setUserName("余兵");
        userInfo.setAge(scopeAge);
        userInfo.setCreateTime(new Date());
        scopeAge++;
        return userInfo;
    }

}
