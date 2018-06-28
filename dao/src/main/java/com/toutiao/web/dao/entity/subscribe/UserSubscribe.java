package com.toutiao.web.dao.entity.subscribe;

import java.util.Date;

public class UserSubscribe {
    private Integer id;

    private Integer userId;

    private Object userSubscribeMap;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Object getUserSubscribeMap() {
        return userSubscribeMap;
    }

    public void setUserSubscribeMap(Object userSubscribeMap) {
        this.userSubscribeMap = userSubscribeMap;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}