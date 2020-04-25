package com.darben.execdatareids.controller;

import com.darben.execdatareids.common.base.BaseController;
import com.darben.execdatareids.model.UserModel;
import com.darben.execdatareids.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 用户前端控制器
 * @author: darben
 * @create: 2020-03-09 16:06
 */
@Slf4j
@RequestMapping(value = "/user")
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
    *@Description: 保存用户信息
    *@Param: 
    *@return: 
    *@date: 2020/3/9
    */
    @RequestMapping(value = "/add")
    public String addUser(HttpServletRequest request){
        String params = request.getParameter("params");
        log.info("->request params:"+params);
        UserModel userModel = paramsToBeanByDesParams(UserModel.class,params);

        userService.saveUser(userModel);
        return "success";
    }

    /**
    *@Description: 查询用户信息
    *@Param:
    *@return:
    *@date: 2020/3/9
    */
    @RequestMapping(value = "/get",produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getUser(HttpServletRequest request){
        String id = request.getParameter("id");
        return userService.getUserModelById(id);
    }

}
