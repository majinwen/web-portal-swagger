package com.toutiao.web.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * RedisUtil工具类
 */

public class RedisUtils {

    //Redis服务器IP
    @Value("${spring.redis.ip}")
    private static String ip;

    //Redis的端口号
    @Value("${spring.redis.port}")
    private static int port;

    //访问密码
    @Value("${spring.redis.AUTH}")
    private static String AUTH;

    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    @Value("${spring.redis.maxTotal}")
    private static int maxTotal;

    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    @Value("${spring.redis.maxIdle}")
    private static int maxIdle;

    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    @Value("${spring.redis.maxWaitMillis}")
    private static int maxWaitMillis;
    @Value("${spring.redis.timeout}")
    private static int timeout;

    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    @Value("${spring.redis.testOnBorrow}")
    private static boolean testOnBorrow;

    private static JedisPool pool = null;
    private static final Logger logger = Logger.getLogger(RedisUtils.class);

    /**
     * 初始化Redis连接池
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(maxIdle);
            config.setMaxWaitMillis(maxWaitMillis);
            config.setTestOnBorrow(testOnBorrow);
            pool = new JedisPool(config, ip, port, timeout, AUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     */
    public static synchronized Jedis getJedis() {
        try

        {
            if (pool != null)

            {

                return pool.getResource();

            } else

            {
                return null;

            }
        } catch (Exception e) {

            logger.info("连接池连接异常");

            return null;

        }
    }

    /**
     * @param @param  key
     * @param @param  seconds
     * @param @return
     * @return boolean 返回类型
     * @Description:设置失效时间
     */
    public static void disableTime(String key, int seconds)

    {

        Jedis jedis = null;

        try

        {

            jedis = getJedis();

            jedis.expire(key, seconds);


        } catch (Exception e) {

            logger.debug("设置失效失败.");

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

    public static boolean addObject(String key, Object obj)

    {
        Jedis jedis = null;
        String value = JSONObject.toJSONString(obj);
        try

        {
            jedis = getJedis();
            String code = jedis.set(key, value);

            if (code.equals("ok"))

            {
                return true;

            }

        } catch (Exception e) {

            logger.debug("插入数据有异常.");

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
    public static Object get(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            Object value = jedis.get(key);
            return value;
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
    public static String getValue(String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = getJedis();
            value = jedis.get(key);
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
    public static boolean set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis != null) {
                jedis.set(key, value);
            }
            return true;
        } catch (Exception e) {
            logger.error("Redis缓存设置key值 出错！", e);
            return false;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 判断key是否存在
     */
    public static boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis == null) {
                return false;
            } else {
                return jedis.exists(key);
            }
        } catch (Exception e) {
            logger.error("Redis缓存判断key是否存在 出错！", e);
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

    public static boolean delKey(String key)

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

            logger.debug("删除key异常.");

            return false;

        } finally {

            returnResource(jedis);

        }

        return false;

    }


    /**
     * 释放jedis资源
     *
     * @param jedis
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            pool.returnResource(jedis);
        }
    }

    /**
     * incr(key)：名称为key的string增1操作
     */
    public static boolean incr(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.incr(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnResource(jedis);
        }
    }

    //*************** 操作map****************start
    public static <K, V> boolean setMap(String key, Map<String, V> map) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis != null) {
                Set<Map.Entry<String, V>> entry = map.entrySet();
                for (Iterator<Map.Entry<String, V>> ite = entry.iterator(); ite.hasNext(); ) {
                    Map.Entry<String, V> kv = ite.next();
                    if (kv.getValue() instanceof String) {
                        jedis.hset(key, kv.getKey(), (String) kv.getValue());
                    } else if (kv.getValue() instanceof List) {
                        jedis.hset(key, kv.getKey(), JSONArray.toJSONString(kv.getValue()).toString());
                    } else {
                        jedis.hset(key, kv.getKey(), JSONObject.toJSONString(kv.getValue()).toString());
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnResource(jedis);
        }
    }

    public static boolean setMapKey(String key, String mapKey, Object value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis != null) {
                if (value instanceof String) {
                    jedis.hset(key, mapKey, String.valueOf(value));
                } else if (value instanceof List) {
                    jedis.hset(key, mapKey, JSONArray.toJSONString(value).toString());
                } else {
                    jedis.hset(key, mapKey, JSONObject.toJSONString(value).toString());
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * seconds key和value 保存的有效时间（单位：秒）
     *
     * @return
     */
    public static boolean setMapKeyExpire(String key, String mapKey, Object value, int seconds) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis != null) {
                if (value instanceof String) {
                    jedis.hset(key, mapKey, String.valueOf(value));
                } else if (value instanceof List) {
                    jedis.hset(key, mapKey, JSONArray.toJSONString(value).toString());
                } else {
                    jedis.hset(key, mapKey, JSONObject.toJSONString(value).toString());
                }
                jedis.expire(key, seconds);
                return true;
            } else {
                return false;
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
    public static boolean setExpire(String key, int second) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.expire(key, second);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnResource(jedis);
        }

    }


}
