package com.toutiao.web.apiimpl.authentication;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * zhangjinglei 2017/10/11 下午3:52
 */
@Data
@Component
public class RedisConfig {



    @Value("${session.redis.maxTotal}")
    private int maxTotal;

    @Value("${session.redis.maxIdle}")
    private int maxIdle;

    @Value("${session.redis.maxWaitMillis}")
    private int maxWaitMillis;

    @Value("${session.redis.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${session.redis.testOnReturn}")
    private boolean testOnReturn;

    @Value("${session.redis.ip}")
    private String ip;

    @Value("${session.redis.port}")
    private int port;

    @Value("${session.redis.timeout}")
    private int timeout;

    @Value("${session.livetimeseconds}")
    private int livetimeseconds;
}
