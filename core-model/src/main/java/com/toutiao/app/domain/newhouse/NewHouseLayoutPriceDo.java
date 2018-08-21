package com.toutiao.app.domain.newhouse;

import lombok.Data;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-08-17
 * Time:   12:22
 * Theme:
 */
@Data
public class NewHouseLayoutPriceDo {

    /**
     * 最小价格
     */
    private Double houseMinPrice;
    /**
     * 最大价格
     */
    private Double houseMaxPrice;
}
