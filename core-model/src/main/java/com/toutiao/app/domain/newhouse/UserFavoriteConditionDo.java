package com.toutiao.app.domain.newhouse;

import lombok.Data;

@Data
public class UserFavoriteConditionDo {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 区域id
     */
    private String[] districtId;
    /**
     * 起始价格
     */
    private Double beginPrice;
    /**
     * 结束价格
     */
    private Double endPrice;
    /**
     * 户型id
     */
    private String[] layoutId;
    /**
     * 城市
     */
    private String city;

    /**
     * 定制类型
     */
    private Integer conditionType = null;

    /**
     * 租房类型1整租2合租
     */
    private Integer rentType = null;
}
