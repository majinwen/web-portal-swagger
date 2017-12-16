package com.toutiao.web.common.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

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
    @Value("${spring.redis.inIdle}")
    private static int minIdle;

    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    @Value("${spring.redis.maxWaitMillis}")
    private static int maxWaitMillis;
    @Value("${spring.redis.timeout}")
    private static int timeout;

    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    @Value("${spring.redis.testOnBorrow}")
    private static boolean testOnBorrow;

    @Value("${spring.redis.testOnReturn}")
    private static boolean testOnReturn;

    @Value("${spring.redis.testWhileIdle}")
    private static boolean testWhileIdle;

    public static JedisPoolConfig c = new JedisPoolConfig(); // 连接池配置

    public static JedisPool jedisPool = null; // 连接池
    static {
        // c.setBlockWhenExhausted(true); // 连接耗尽则阻塞
        c.setLifo(true); // 后进先出
        c.setMaxIdle(maxIdle); // 最大空闲连接数为10
        c.setMinIdle(minIdle); // 最小空闲连接数为0
        c.setMaxTotal(maxTotal); // 最大连接数为100
        c.setMaxWaitMillis(maxWaitMillis); // 设置最大等待毫秒数：无限制
        c.setTestOnBorrow(testOnBorrow); // 获取连接时是否检查连接的有效性：是
        c.setTestWhileIdle(testWhileIdle); // 空闲时是否检查连接的有效性：是
        c.setTestOnReturn(testOnReturn);
        jedisPool = new JedisPool(c, ip, port, timeout, AUTH); // 初始化连接池
    }
    /**
     * 获取Jedis连接
     *
     * @return Jedis连接
     */
    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

    /**
     * 释放jedis资源
     */
    public static void returnResource(Jedis jedis) {
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
    public static void disableTime(String key, int seconds) {
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

    public static boolean addObject(String key, Object obj) {
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
    public static Object get(String key) {
        Jedis jedis = null;
        Object value=null;
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
    public static String getValue(String key) {
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
    public static boolean set(String key, String value) {
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
    public static boolean set2(String key, String value, int expire) {
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
    public static void setObject(String key, int expire, Object value) {
        Jedis jedis = getJedis();
        jedis.set(key.getBytes(), SerializeUtil.serialize(value));
        jedis.expire(key.getBytes(), expire);
    }

    /**
     * 设置对象
     *
     * @param key
     */
    public static Object getObject(String key) {
        Jedis jedis = getJedis();
        return SerializeUtil.unserialize(jedis.get(key.getBytes()));

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


            return false;

        } finally {

            returnResource(jedis);

        }

        return false;

    }

    /**
     * incr(key)：名称为key的string增1操作
     */
    public static boolean incr(String key) {
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
    public static boolean setExpire(String key, int second) {
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

    public static void main(String[] args) {
        Jedis jedis = RedisUtils.getJedis();
        String s = jedis.get("15601676403");
        String value = RedisUtils.getValue("15601676403");
        System.out.println(s+value);
    }

}