package com.toutiao.app.domain.favorite;

import lombok.Data;

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
     * 均价
     */
    private Double avgPrice;
    /**
     * 小区名称
     */
    private String name;
    /**
     * 标题图
     */
    private String titleImage;
    /**
     * 是否下架(0-未下架, 1-下架)
     */
    private Short status;
    /**
     * 是否删除(0-未删除，1-已删除)
     */
    private Short isDel;
}
