package com.toutiao.app.domain.favorite;

import lombok.Data;

@Data
public class PlotsAddFavoriteDoQuery {
    /**
     * 小区id
     */
    private Integer buildingId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 均价
     */
    private Double averagePrice;
    /**
     * 小区名称
     */
    private String buildingName;
    /**
     * 标题图
     */
    private String buildingImages;
    /**
     * 是否下架(0-未下架, 1-下架)
     */
    private Short status;
    /**
     * 是否删除(0-未删除，1-已删除)
     */
    private Short isDel;

    private Integer cityId;
}
