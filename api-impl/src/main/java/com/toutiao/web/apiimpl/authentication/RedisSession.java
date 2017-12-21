package com.toutiao.web.apiimpl.authentication;

import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.common.util.SerializeUtil;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * zhangjinglei 2017/10/11 下午2:24
 */

@Component
public class RedisSession {

    private RedisConfig redisConfig;

    private JedisPool jedisPool = null;

    public RedisSession(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(redisConfig.getMaxTotal());
        config.setMaxIdle(redisConfig.getMaxIdle());
        config.setMaxWaitMillis(redisConfig.getMaxWaitMillis());
        config.setTestOnBorrow(redisConfig.isTestOnBorrow());
        config.setTestOnReturn(redisConfig.isTestOnReturn());

        jedisPool = new JedisPool(config, redisConfig.getIp(), redisConfig.getPort(), redisConfig.getTimeout(), redisConfig.getAuth());

    }
    /*public boolean hasLogin(String userNo){
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
    }*/

    /**
     * 获取Jedis连接
     *
     * @return Jedis连接
     */
    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    /**
     * 释放jedis资源
     */
    public void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * @param @param  key
     * @param @param  seconds
     * @param @return
     * @return boolean 返回类型
     * @Description:设置失效时间
     */
    public void disableTime(String key, int seconds) {
        Jedis jedis = null;
        try

        {

            jedis = getJedis();

            jedis.expire(key, seconds);


        } catch (Exception e) {

        } finally {

            returnResource(jedis);

        }

    }

    /**
     * @param @param  key
     * @param @param  obj
     * @param @return
     * @return boolean 返回类型
     * @Description:插入对象
     */

    public boolean addObject(String key, Object obj) {
        Jedis jedis = null;
        String value = JSONObject.toJSONString(obj);
        try {
            jedis = getJedis();
            String code = jedis.set(key, value);

            if (code.equals("ok")) {
                return true;
            }

        } catch (Exception e) {

            return false;

        } finally {
            returnResource(jedis);
        }
        return false;
    }

    /**
     * 根据key 获取对象
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        Jedis jedis = null;
        Object value = null;
        try {
            jedis = getJedis();
            if (jedis != null) {
                value = jedis.get(key);
                return value;
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 根据key 获取对象
     *
     * @param key
     * @return
     */
    public String getValue(String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = getJedis();
            if (jedis != null) {
                value = jedis.get(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 向缓存中设置字符串内容
     *
     * @param key   key
     * @param value value
     * @return
     * @throws Exception
     */
    public boolean set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis != null) {
                jedis.set(key, value);
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 向缓存中设置字符串内容
     *
     * @param key   key
     * @param value value
     * @return
     * @throws Exception
     */
    public boolean set2(String key, String value, int expire) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis != null) {
                jedis.set(key, value);
                jedis.expire(key, expire);
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 保存对象
     *
     * @param key
     * @param expire
     * @param value
     */
    public void setObject(String key, int expire, Object value) {
        Jedis jedis = getJedis();
        jedis.set(key.getBytes(), SerializeUtil.serialize(value));
        jedis.expire(key.getBytes(), expire);
    }

    /**
     * 设置对象
     *
     * @param key
     */
    public Object getObject(String key) {
        Jedis jedis = getJedis();
        return SerializeUtil.unserialize(jedis.get(key.getBytes()));

    }


    /**
     * 判断key是否存在
     */
    public boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis == null) {
                return false;
            } else {
                return jedis.exists(key);
            }
        } catch (Exception e) {
            return false;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * @param @param  key
     * @param @return
     * @return boolean 返回类型
     * @Description:删除key
     */

    public boolean delKey(String key)

    {

        Jedis jedis = null;

        try

        {

            jedis = getJedis();

            Long code = jedis.del(key);

            if (code > 1)

            {

                return true;

            }

        } catch (Exception e) {


            return false;

        } finally {

            returnResource(jedis);

        }

        return false;

    }

    /**
     * incr(key)：名称为key的string增1操作
     */
    public boolean incr(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis == null) {
                return false;
            } else {
                jedis.incr(key);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 设置key的过期时间
     */
    public boolean setExpire(String key, int second) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis == null) {
                return false;
            } else {
                jedis.expire(key, second);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnResource(jedis);
        }
    }

    public void main(String[] args) {
        RedisConfig redisConfig=new RedisConfig();
        RedisSession redisSession=new RedisSession(redisConfig);
        Jedis jedis = redisSession.getJedis();
        String set = jedis.set("15601676403", "1");
        System.out.println(set);
        /*String s = jedis.get("15601676403");
        String value = redisSession.getValue("15601676403");
        System.out.println(s + value);*/
    }


//    l-dev2.ops.bj3.nashwork.com
}
