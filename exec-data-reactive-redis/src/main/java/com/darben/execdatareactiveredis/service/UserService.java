package com.darben.execdatareactiveredis.service;

import com.darben.execdatareactiveredis.model.UserModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @description: 用户业务层接口类
 * @author: darben
 * @create: 2020-03-13 16:20
 */
public interface UserService {
    
    /**
    *@Description:
    *@Param: 
    *@return: 
    *@date: 2020/3/14
    */
    Mono<Object> getUserInfo(String id);
    
    /**
    *@Description:
    *@Param: 
    *@return: 
    *@date: 2020/3/13
    */
    Mono<Object> getUserModelById(String id);
    
    /**
    *@Description: 保存
    *@Param: 
    *@return: 
    *@date: 2020/3/13
    */
    Mono<Boolean> saveUserModel(UserModel userModel);

}
