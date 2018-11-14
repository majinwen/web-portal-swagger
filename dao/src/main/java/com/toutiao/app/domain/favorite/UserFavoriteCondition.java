package com.toutiao.app.domain.favorite;

import lombok.Data;

import java.util.Date;

@Data
public class UserFavoriteCondition {
    /**
     * 条件储存id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除(0-未删除，1-已删除)
     */
    private Integer isDel;

    /**
     * 条件
     */
    private Object condition;
    /**
     * 城市
     */
    private Integer cityId;

}