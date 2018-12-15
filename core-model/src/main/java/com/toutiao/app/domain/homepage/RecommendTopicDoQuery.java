package com.toutiao.app.domain.homepage;

import lombok.Data;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-08-07
 * Time:   11:35
 * Theme:
 */
@Data
public class RecommendTopicDoQuery {


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
     * 户型
     */
    private String[] layoutId;


}
