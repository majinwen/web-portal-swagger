package com.toutiao.web.apiimpl.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * zhangjinglei 2017/10/11 下午2:24
 */

@Component
public class RedisSession {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private RedisConfig redisConfig;

    private JedisPool jedisPool=null;

    public RedisSession(RedisConfig redisConfig){
        this.redisConfig=redisConfig;
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(redisConfig.getMaxTotal());
        config.setMaxIdle(redisConfig.getMaxIdle());
        config.setMaxWaitMillis(redisConfig.getMaxWaitMillis());
        config.setTestOnBorrow(redisConfig.isTestOnBorrow());
        config.setTestOnReturn(redisConfig.isTestOnReturn());

        jedisPool = new JedisPool(config,redisConfig.getIp(),redisConfig.getPort(),redisConfig.getTimeout());

    }
    public boolean hasLogin(String userNo){
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.exists("crm_"+userNo);

        }
        catch (Exception e) {
            logger.error("session redis 异常",e);
        }
        return false;
    }

    public boolean login(String userNo){
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.setex("crm_"+userNo,redisConfig.getLivetimeseconds(),"1");

        }
        catch (Exception e) {
            logger.error("session redis 异常",e);
            return false;
        }
        return true;
    }

    public void logout(String userNo){
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.del("crm_"+userNo);

        }
        catch (Exception e) {
            logger.error("session redis 异常",e);
        }
    }


//    l-dev2.ops.bj3.nashwork.com
}
