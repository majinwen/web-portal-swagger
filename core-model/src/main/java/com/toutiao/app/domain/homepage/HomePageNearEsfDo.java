package com.toutiao.app.domain.homepage;

import lombok.Data;

@Data
public class HomePageNearEsfDo {
    /**
     * 二手房房源id
     */
    private String houseId;
    /**
     * 房源标题
     */
    private String houseTitle;
    /**
     * 室
     */
    private Integer room;
    /**
     * 厅
     */
    private Integer hall;
    /**
     * 房源总价
     */
    private Double houseTotalPrices;
    /**
     * 建筑面积
     */
    private Double buildArea;
    /**
     * 房源标签
     */
    private String[] tagsName;
    /**
     * 是否属于top50小区
     */
    private String isCommunityTopHouse;
    /**
     * 是否是降价房
     */
    private String isCutPrice;
    /**
     * 距离
     */
    private Double distance;

}
