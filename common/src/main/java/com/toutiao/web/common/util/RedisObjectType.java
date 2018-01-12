package com.toutiao.web.common.util;

/**
 * Created by IntelliJ IDEA.
 *
 * @Description: 统一定义Redis中存储的各种对象的Key前缀和超时时间(单位：秒)
 */
public enum RedisObjectType {

    USER_PHONE_VALIDATECODE("phone_validate_code:", 60 * 2), // 手机验证码
    USER_PHONE_VALIDATE_OK("phone_validate_ok:", 60 * 3),    // 手机验证码通过标识
    USER_IMAGE_VALIDATECODE("image_code:", 60*10),  // 图片验证码
    SYS_USER_MANAGER("sys_user:", 60 * 60 * 4); // 系统用户缓存key前缀和过期时间
    private String prefix;

    //expire时间（单位秒）
    private int expiredTime;

    RedisObjectType(String prefix, int expiredTime) {
        this.prefix = prefix;
        this.expiredTime = expiredTime;
    }

    public String getPrefix() {
        return prefix;
    }


    public int getExpiredTime() {
        return expiredTime;
    }

    }
