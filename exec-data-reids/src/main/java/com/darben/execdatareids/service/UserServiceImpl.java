package com.darben.execdatareids.service;

import com.darben.execdatareids.common.ModelUtils;
import com.darben.execdatareids.common.RedisUtils;
import com.darben.execdatareids.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @description: 用户业务层实现类
 * @author: darben
 * @create: 2020-03-09 16:07
 */
@Component
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void saveUser(UserModel userModel) {
        Map<String,Object> userMap = ModelUtils.modelToMap(userModel);
        boolean dealFlag = redisUtils.hMset(userModel.getId().toString(),userMap);
        log.info("<- deal falg :"+dealFlag);
    }

    @Override
    public Map<Object,Object> getUserModelById(String id) {

        return redisUtils.hMget(id);

    }
}
