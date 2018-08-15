package com.toutiao.app.domain.plot;

import lombok.Data;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-07-31
 * Time:   19:04
 * Theme:
 */

@Data
public class PlotsHousesDomain {

    /**
     * 二手房总量
     */
    private Long sellHouseCount;

    /**
     * 出租房总量
     */
    private Long rentCount;

    /**
     * 小区均价
     */
    private Double avgPrice;

    /**
     * 租房最低价
     */
    private Double startPrice;



}
