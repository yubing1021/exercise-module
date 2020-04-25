package com.darben.execdatareids.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @description: Redis帮助类组件
 * @author: darben
 * @create: 2020-03-09 15:39
 */
@Component
@Slf4j
public class RedisUtils {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public RedisUtils(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
    *@Description:
    *@Param:
     * key key键
     * time 失效时间，单位秒
    *@return:
    *@date: 2020/3/9
    */
    public boolean expire(String key,long time){
        try {
            if (time>0){
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
            }
            return true;
        }
        catch (Exception e){
            log.error("-> 设置redis key失效时间异常 "+ e);
            e.printStackTrace();
            return false;
        }
    }

    /**
    *@Description: 查询ke过期时间
    *@Param: 
    *@return: 时间(秒) 返回0代表为永久有效
    *@date: 2020/3/9
    */
    public long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     *@Description: 删除缓存
     *@Param:  集合，多个一起处理
     *@return:
     *@date: 2020/3/9
     */
    public void delKeys(String ... key){
        if(key!=null && key.length>0){
            if(key.length == 1){
                redisTemplate.delete(key[0]);
            }
            else{
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
    *@Description: 删除缓存
    *@Param: 单个进行删除
    *@return: 
    *@date: 2020/3/9
    */
    public boolean del(String key){
        if(!StringUtils.isEmpty(key)){
            return redisTemplate.delete(key);
        }
        return true;
    }

    /**
    *@Description: 持久化key的值
    *@Param:
    *@return:
    *@date: 2020/3/11
    */
    public boolean persist(String key){
        return redisTemplate.persist(key);
    }

    //String结构类型处理
    //..

    /**
    *@Description: 普通缓存获取
    *@Param: 
    *@return:
    *@date: 2020/3/9
    */
    public Object get(String key){
        return StringUtils.isEmpty(key)?null:redisTemplate.opsForValue().get(key);
    }

    /**
    *@Description: 普通缓存存储
    *@Param: 
    *@return: 
    *@date: 2020/3/9
    */
    public boolean set(String key,Object value){
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        }
        catch (Exception e){
            log.error("->set occur error "+e);
            e.printStackTrace();
            return false;
        }
    }

    /**
     *@Description: 普通缓存存储+设置过期时间
     *@param key   键
     *@param value 值
     *@param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     *@return:
     *@date: 2020/3/9
     */
    public boolean set(String key,Object value,long time){
        try {
            redisTemplate.opsForValue().set(key,value,time,TimeUnit.SECONDS);
            return true;
        }
        catch (Exception e){
            log.error("->set occur error "+e);
            e.printStackTrace();
            return false;
        }
    }

    /**
    *@Description: incrby 递增
    *@Param: 
    *@return: 
    *@date: 2020/3/11
    */
    public long incrby(String key,long delta){
        if(delta<0){
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key,delta);
    }
    
    /**
    *@Description: decryb 递减
    *@Param: 
    *@return: 
    *@date: 2020/3/11
    */
    public long decrby(String key,long delta){
        if(delta<0){
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().decrement(key,-delta);
    }

    //list链表结构类型处理
    //..
    
    /**
    *@Description: 查询key对应的list缓存的内容
    *@Param: 
    *@return: 
    *@date: 2020/3/11
    */
    public List<Object> lGet(String key,long start,long end){
        try {
            return redisTemplate.opsForList().range(key,start,end);
        }
        catch (Exception e){
            log.error("lGet occur error. "+e);
            e.printStackTrace();
            return null;
        }
    }

    /**
    *@Description: 查询List对应的长度
    *@Param: 
    *@return: 
    *@date: 2020/3/11
    */
    public long lGetListSize(String key){
        try {
            return redisTemplate.opsForList().size(key);
        }
        catch (Exception e){
            log.error("lGetListSize occur error."+e);
            e.printStackTrace();
            return 0;
        }
    }
    
    /**
    *@Description: 通过索引找到list中的值
    *@Param: 
    *@return: 
    *@date: 2020/3/11
    */
    public Object lGetByIndex(String key,long index){
        try {
            return redisTemplate.opsForList().index(key,index);
        }
        catch (Exception e){
            log.error("lGetByIndex occur error. "+e);
            e.printStackTrace();
            return null;
        }
    }

    /**
    *@Description: 将数据存储在list缓存
    *@Param:
    *@return:
    *@date: 2020/3/11
    */
    public boolean rPush(String key,Object value){
        try {
            redisTemplate.opsForList().rightPush(key,value);
            return true;
        }
        catch (Exception e){
            log.error("rPush occur error. "+ e);
            e.printStackTrace();
            return false;
        }
    }

    /**
    *@Description:
    *@Param:
    *@return:
    *@date: 2020/3/11
    */
    public boolean rPush(String key,Object value,long time){
        try {
            redisTemplate.opsForList().rightPush(key,value);
            if(time > 0){
                expire(key,time);
            }
            return true;
        }
        catch (Exception e){
            log.error("rPush with expire occur error. "+ e);
            e.printStackTrace();
            return false;
        }
    }

    /**
    *@Description: 将list进行存储
    *@Param:
    *@return:
    *@date: 2020/3/11
    */
    public boolean rPush(String key,List<Object> list){
        try {
            redisTemplate.opsForList().rightPushAll(key,list);
            return true;
        }
        catch (Exception e){
            log.error("rPushAll  occur error. "+ e);
            return false;
        }
    }

    /**
     *@Description: 将list进行存储+过期时间
     *@Param:
     *@return:
     *@date: 2020/3/11
     */
    public boolean rPush(String key,List<Object> list,long time){
        try {
            redisTemplate.opsForList().rightPushAll(key,list);
            if(time>0){
                expire(key,time);
            }
            return true;
        }
        catch (Exception e){
            log.error("rPushAll  occur error. "+ e);
            return false;
        }
    }

    /**
    *@Description: 根据索引修改list中的某条数据
    *@Param: 
    *@return: 
    *@date: 2020/3/11
    */
    public boolean lUpdateIndexValue(String key,long index,Object value){
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            log.error("lUpdateIndexValue occur error."+e);
            e.printStackTrace();
            return false;
        }
    }

    //Set集合结构类型处理
    //..

    /**
    *@Description: 查询Set中所有值，根据key
    *@Param:
    *@return: 
    *@date: 2020/3/13
    */
    public Set<Object> smembers(String key){
        try {
            return redisTemplate.opsForSet().members(key);
        }catch (Exception e){
            log.error("-> smembers occur error. "+e);
            e.printStackTrace();
            return null;
        }
    }
    
    /**
    *@Description: 将数据存储在Set缓存
    *@Param:
    *@return:
    *@date: 2020/3/13
    */
    public long sSet(String key, Object ... values){
        try {
            return redisTemplate.opsForSet().add(key,values);
        }
        catch (Exception e){
            log.error("-> sSet occur error. "+e);
            e.printStackTrace();
            return 0;
        }
    }

    /**
    *@Description: 将数据存储在Set缓存，并设置过期时间
    *@Param:
    *@return: 
    *@date: 2020/3/13
    */
    public long sSet(String key,long time,Object ... values){
        try {
            long count = redisTemplate.opsForSet().add(key,values);
            if(time > 0){
                expire(key,time);
            }
            return count;
        }
        catch (Exception e){
            log.error("-> sSet occur error. "+e);
            e.printStackTrace();
            return 0;
        }
    }

    /**
    *@Description: 获取Set集合大小
    *@Param: 
    *@return: 
    *@date: 2020/3/13
    */
    public long sGetSetSize(String key){
        return redisTemplate.opsForSet().size(key);
    }

    /**
    *@Description: 移除set和集合values元素
    *@Param: 
    *@return: 
    *@date: 2020/3/13
    */
    public long setRemove(String key,Object ... values){
        try {
            return redisTemplate.opsForSet().remove(key,values);
        }
        catch (Exception e){
            log.error("-> setRemove occur error. "+ e);
            e.printStackTrace();
            return 0;
        }

    }
    /**
     * 根据value从一个set中查询,是否存在
     * @param key 键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(String key,Object value){
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            log.error("-> sHasKey occur error. "+ e);
            e.printStackTrace();
            return false;
        }
    }

    //HashMap结构类型处理
    //..

    /**
    *@Description: 获取key对应的map指定profile的值
    *@Param:
    *@return:
    *@date: 2020/3/11
    */
    public Object hGet(String key,String profile){
        return redisTemplate.opsForHash().get(key,profile);
    }
    
    /**
    *@Description: 获取key对应的Hash值
    *@Param:
    *@return:
    *@date: 2020/3/11
    */
    public Map<Object,Object> hMget(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
    *@Description: 存储值HashMap
    *@Param: 
    *@return: 
    *@date: 2020/3/11
    */
    public boolean hMset(String key,Map<String,Object> map){
        try {
            redisTemplate.opsForHash().putAll(key,map);
            return true;
        }
        catch (Exception e){
            log.error("->hmset occur error "+e);
            e.printStackTrace();
            return false;
        }
    }

    /**
    *@Description: 存储HashMap + 过期时间
    *@Param:
    *@return:
    *@date: 2020/3/11
    */
    public boolean hMset(String key,Map<String,Object> map, long time){
        try{
            redisTemplate.opsForHash().putAll(key,map);
            if(time>0){
                expire(key,time);
            }
            return true;
        }
        catch (Exception e){
            log.error("->hmset with expire time occur error "+e);
            e.printStackTrace();
            return false;
        }
    }
    
    /**
    *@Description: 向一张hash表中放入数据,如果不存在将创建
    *@Param:
    *@return:
    *@date: 2020/3/11
    */
    public boolean hSet(String key,String profiel,Object value,Long time){
        try{
            redisTemplate.opsForHash().put(key,profiel,value);
            if(time !=null && time.longValue()>0){
                expire(key,time);
            }
            return true;
        }
        catch (Exception e){
            log.error("->hset with expire time occur error "+e);
            e.printStackTrace();
            return false;
        }
    }

    /**
    *@Description: 删除Hash表中的值
    *@Param: 
    *@return: 
    *@date: 2020/3/11
    */
    public void hDel(String key,Object ... profile){
        redisTemplate.opsForHash().delete(key,profile);
    }

    /**
    *@Description: 判断hash表中是否有key值
    *@Param:
    *@return:
    *@date: 2020/3/11
    */
    public boolean hHasKey(String key,String profile){
        return redisTemplate.opsForHash().hasKey(key,profile);
    }


}
