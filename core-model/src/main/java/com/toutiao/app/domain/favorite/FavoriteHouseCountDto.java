package com.toutiao.app.domain.favorite;

import lombok.Data;

/**
 * Created by wk on 2018/11/30.
 */
@Data
public class FavoriteHouseCountDto {

    /**
     * 新房
     */
    private Integer[] newHouseIds;

    /**
     * 二手房
     */
    private String[] sellHouseIds;

    /**
     * 小区
     */
    private Integer[] plotIds;

    /**
     * 租房
     */
    private String[] rentHouseIds;

}
