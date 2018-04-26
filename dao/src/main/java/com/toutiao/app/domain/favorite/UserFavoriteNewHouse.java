package com.toutiao.app.domain.favorite;

import lombok.Data;

import java.util.Date;
@Data
public class UserFavoriteNewHouse {
    /**
     * 新房收藏id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 新房id
     */
    private Integer newHouseId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否删除(0-未删除，1-已删除)
     */
    private Short isDel;

}