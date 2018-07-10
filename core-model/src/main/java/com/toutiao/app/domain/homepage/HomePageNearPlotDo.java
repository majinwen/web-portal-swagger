package com.toutiao.app.domain.homepage;

import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

@Data
public class HomePageNearPlotDo {
    /**
     * 小区编号
     */
    @ChangeName("buildingId")
    private Integer id;
    /**
     * 小区名称/楼盘名称
     */
    @ChangeName("buildingName")
    private String rc;
    /**
     * 别名
     */
    @ChangeName("buildingNickName")
    private String alias;
    /**
     * 区域编号
     */
    @ChangeName("districtId")
    private String areaId;
    /**
     * 区域
     */
    @ChangeName("districtName")
    private String area;
    /**
     * 商圈编号
     */
    @ChangeName("areaId")
    private String tradingAreaId;
    /**
     * 商圈
     */
    @ChangeName("areaName")
    private String tradingArea;
    /**
     * 地址
     */
    private String address;
    /**
     * 坐标
     */
    private String location;
    /**
     * 均价
     */
    @ChangeName("averagePrice")
    private Double avgPrice;
    /**
     * 距离
     */
    private Double distance;
    /**
     * 商圈均价排名
     */
    private Integer rankAvgInBizcircle;
    /**
     * 在售房源套数
     */
    private Integer houseCount;
    /**
     * 起始价格
     */
    private Double lowestPrice;

}
