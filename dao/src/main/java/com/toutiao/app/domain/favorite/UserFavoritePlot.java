package com.toutiao.app.domain.favorite;

import lombok.Data;

import java.util.Date;
@Data
public class UserFavoritePlot {
    /**
     * 收藏小区id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 小区id
     */
    private Integer villageId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否删除(0-未删除，1-已删除)
     */
    private Short isDel;

}