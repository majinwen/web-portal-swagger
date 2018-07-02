package com.toutiao.web.dao.entity.subscribe;

import lombok.Data;

import java.util.Date;

@Data
public class UserSubscribe {
    private Integer id;

    private Integer userId;

    private Object userSubscribeMap;

    private Date createTime;

    private Date updateTime;
}