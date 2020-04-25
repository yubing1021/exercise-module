package com.darben.execdatareactiveredis.service;

import com.darben.execdatareactiveredis.common.ModelUtils;
import com.darben.execdatareactiveredis.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @description: 用户业务层接口实现类
 * @author: darben
 * @create: 2020-03-13 16:21
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private ReactiveRedisTemplate<String,Object> reactiveRedisTemplate;

    @Override
    public Mono<Object> getUserInfo(String id) {
        return reactiveRedisTemplate.opsForValue().get(id);
    }

    @Override
    public Mono<Object> getUserModelById(String id) {
        Mono<Object> mono = reactiveRedisTemplate.opsForHash().get(id, UserModel.class);
        return mono;
    }

    @Override
    public Mono<Boolean> saveUserModel(UserModel userModel) {
        Map<String,Object> modelMap = ModelUtils.modelToMap(userModel);
        return reactiveRedisTemplate.opsForHash().putAll(userModel.getId().toString(),modelMap);
    }
}
