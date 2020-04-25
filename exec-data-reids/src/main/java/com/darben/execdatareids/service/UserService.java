package com.darben.execdatareids.service;

import com.darben.execdatareids.model.UserModel;

import java.util.Map;

public interface UserService {

    /**
    *@Description: 保存用户信息
    *@Param:
    *@return:
    *@date: 2020/3/9
    */
    void saveUser(UserModel userModel);
    
    /**
    *@Description:
    *@Param: 
    *@return: 
    *@date: 2020/3/9
    */
    Map<Object,Object> getUserModelById(String id);

}
