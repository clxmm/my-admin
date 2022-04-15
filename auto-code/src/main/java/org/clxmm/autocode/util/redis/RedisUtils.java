package org.clxmm.autocode.util.redis;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 写入String型 [ 键，值]
     *
     * @param key
     * @param val
     * @return
     */
    public boolean set(final String key, Object val) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, val);
            result = true;
        } catch (Exception e) {
            log.error("RedisUtils : " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 写入String型,顺便带有过期时间 [ 键，值]
     */
    public boolean setWithTime(final String key, Object val, int seconds) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, val, seconds, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            log.error("RedisUtils : " + e.getMessage());
            e.printStackTrace();
        }


        return result;
    }


    /**
     * 判断缓存中key 是否有对应的value
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除对应的value
     */
    public void remove(String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }


    /**
     * 批量删除key对应的value
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }


    /**
     * 读取缓存
     */
    public Object get(final String key) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        Object o = operations.get(key);
        return o;
    }


    /**************************** hash ****************/
    /**
     * 哈希 添加
     * hash 一个键值(key->value)对集合
     */
    public void hmSet(String key, Object hashKey, Object value) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key, hashKey, value);
    }


    /**
     * Hash获取数据
     */
    public Object hmGet(String key, String hashKey) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.get(key, hashKey);
    }

    /**************************** hash ****************/


    /**************************** list ****************/


    /**
     * 列表添加
     * list:lpush key value1
     */
    public void lPush(String k, Object v) {

        ListOperations listOperations = redisTemplate.opsForList();
        listOperations.rightPush(k, v);

    }


    /**
     * 列表List获取
     * lrange： key 0 10 (读取的个数 从0开始 读取到下标为10 的数据)
     */
    public List<Object> lRange(String k, long l, long l1) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(k, l, l1);
    }


    /***************** Set **********/

    /**
     * Set集合添加
     */
    public void addSte(String key, Object value) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key, value);
    }

    /**
     * Set 集合获取
     *
     * @param key
     */
    public Set<Object> getSet(String key) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }


    /*********** ZSet *********/

    /**
     * 有序集合添加 ZSet
     *
     * @param key
     * @param value
     * @param scoure
     */
    public void zAdd(String key, Object value, double scoure) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add(key, value, scoure);
    }


    /**
     * 有序集合获取
     */
    public Set<Object> rangeByScore(String key, double scoure, double scoure1) {

        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();

        return zset.rangeByScore(key, scoure, scoure1);

    }


    /**
     * 根据key获取Set中的所有值
     */
    public Set sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }


    /**
     * 根据value从一个set中查询,是否存在
     */
    public boolean sHaaKey(String key,Object val) {
        try {

            return redisTemplate.opsForSet().isMember(key,val);
        } catch (Exception e) {
            log.error(e.getMessage());
            return  false;
        }

    }



}
