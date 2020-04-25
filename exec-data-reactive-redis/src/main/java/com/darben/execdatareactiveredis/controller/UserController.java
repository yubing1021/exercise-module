package com.darben.execdatareactiveredis.controller;

import com.darben.execdatareactiveredis.model.UserModel;
import com.darben.execdatareactiveredis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @description: 用户信息前端控制器
 * @author: darben
 * @create: 2020-03-13 16:19
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /* @RequestMapping("/get/{id}")
    public Mono<Object> getUserInfo(@PathVariable("id") String id){
        log.info("-> id :"+id);
        Mono<Object> mono = userService.getUserInfo(id);
        return mono;
    }*/

    @RequestMapping("/get/{id}")
    public Mono<Object> getUserById(@PathVariable("id") String id){
        log.info("-> id :"+id);
        return userService.getUserModelById("id");
    }

    @RequestMapping("/add")
    public Mono<Boolean> saveUserModel(@RequestBody UserModel userModel){
        log.info("-> userModel :" + userModel);
        return userService.saveUserModel(userModel);
    }

}
