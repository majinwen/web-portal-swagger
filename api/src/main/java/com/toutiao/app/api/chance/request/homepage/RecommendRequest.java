package com.toutiao.app.api.chance.request.homepage;

import lombok.Data;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-08-07
 * Time:   11:00
 * Theme:
 */
@Data
public class RecommendRequest {


    /**
     * 区域id
     */
    private Integer[] districtId;
    /**
     * 起始价格
     */
    private Double beginPrice;
    /**
     * 结束价格
     */
    private Double endPrice;



}
