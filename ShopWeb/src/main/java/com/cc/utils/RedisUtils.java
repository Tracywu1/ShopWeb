package com.cc.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.TimeUnit;

public class RedisUtils {
    private static final String REDIS_HOST = "localhost";
    private static final int REDIS_PORT = 6379;
    private static JedisPool jedisPool;

    // 初始化 JedisPool
    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(100);
        jedisPoolConfig.setMaxIdle(10);
        jedisPool = new JedisPool(jedisPoolConfig, REDIS_HOST, REDIS_PORT);
    }

    // 获取 Jedis 实例
    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

    // 设置值
    public static void set(String key, String value, long expireTime, TimeUnit milliseconds) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
            if (expireTime > 0) {
                jedis.expire(key, (int) (expireTime / 1000));
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    // 获取值
    public static String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    // 删除值
    public static void delete(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    // 判断键是否存在
    public static boolean hasKey(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}