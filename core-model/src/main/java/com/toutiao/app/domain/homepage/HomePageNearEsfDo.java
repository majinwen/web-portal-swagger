package com.toutiao.app.domain.homepage;

import com.toutiao.web.common.assertUtils.ChangeName;
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
     * 房源标题图
     */
    private String housePhotoTitle;
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
     * 是否是降价房(0:否 1:降 2:涨)
     */
    private String isCutPrice ;
    /**
     * 距离
     */
    private Double distance;
    /**
     * 价格浮动
     */
    private double priceFloat;
    /**
     * 小区名称
     */
    @ChangeName("buildingName")
    private String plotName;
    /**
     * 楼盘ID(楼盘/小区)
     */
    @ChangeName("buildingId")
    private Integer newcode;
    /**
     * 排序规则字段
     */
    private String upTimestamp;
    /**
     * 推荐广告查询uid
     */
    private String uid;


}