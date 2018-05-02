package com.toutiao.app.domain.favorite;

import lombok.Data;

import java.util.Date;

@Data
public class PlotsAddFavoriteDoQuery {
    /**
     * 小区id
     */
    private Integer plotId;
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 是否删除(0-未删除，1-已删除)
     */
    private Short isDel;
}
