package com.darben.execdatareids.common.base;

import com.alibaba.fastjson.JSONObject;

/**
 * @description: 控制器基类
 * @author: darben
 * @create: 2020-03-09 16:14
 */
public class BaseController {

    /**
     * 业务参数转成Bean对象
     * @param t
     */
    public <T> T paramsToBeanByDesParams(Class t,String value){
        return (T) JSONObject.parseObject(value, t);
    }

}
