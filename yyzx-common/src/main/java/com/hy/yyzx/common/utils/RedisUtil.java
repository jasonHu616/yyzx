package com.hy.yyzx.common.utils;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 缓存工具
 */
public class RedisUtil {
    RedisTemplate<String, ?> redisTemplate;
    String keyPrefix;

    public RedisUtil(RedisTemplate<String, ?> redisTemplate, String keyPrefix) {
        this.redisTemplate = redisTemplate;
        this.keyPrefix = keyPrefix;
    }

    private String formatKey(String key){
        if(keyPrefix!=null && !keyPrefix.trim().equalsIgnoreCase(""))return keyPrefix + ":" + key;
        return key;
    }

    public Set<String> keys(final String keyPrefix){
        return redisTemplate.keys(formatKey(keyPrefix));
    }

    public boolean set(final String key, final Object value) {
        return set(key, JSONUtils.toJSONString(value));
    }


    public boolean set(final String key, final Object value, long expire) {
        return set(key, JSONUtils.toJSONString(value), expire);
    }


    public boolean set(final String key, final String value) {
        return set(key, value, -1);
    }


    public boolean set(final String key, final String value, long expire) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                connection.openPipeline();
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.set(serializer.serialize(formatKey(key)), serializer.serialize(value));
                if(expire>-1) {
                    connection.expire(serializer.serialize(formatKey(key)), expire);
                }
                connection.closePipeline();
                return true;
            }
        });
        return result;
    }

    public String get(final String key){
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value =  connection.get(serializer.serialize(formatKey(key)));
                return serializer.deserialize(value);
            }
        });
        return result;
    }

    public <T> T get(final String key, Class<T> clz){
        T result = redisTemplate.execute(new RedisCallback<T>() {
            @Override
            public T doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value =  connection.get(serializer.serialize(formatKey(key)));
                if(value!=null) {
                    String str = serializer.deserialize(value);
                    return JSONUtils.parse(str, clz);
                }
                return null;
            }
        });
        return result;
    }

    public boolean expire(final String key, long expire) {
        return redisTemplate.expire(formatKey(key), expire, TimeUnit.SECONDS);
    }

    public boolean remove(final String key){
        Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.del(serializer.serialize(formatKey(key)));
                return true;
            }
        });
        return result;
    }

    public <T> boolean setList(String key, List<T> list) {
        String value = JSONUtils.toJSONString(list);
        return set(key,value);
    }

    public <T> boolean setList(String key, List<T> list, long expire) {
        String value = JSONUtils.toJSONString(list);
        return set(key,value, expire);
    }

    public <T> List<T> getList(String key, Class<T> clz) {
        String json = get(key);
        if(json!=null){
            List<T> list = JSONUtils.parseList(json, clz);
            return list;
        }
        return null;
    }

}