package com.toutiao.app.domain.plot;

import lombok.Data;

@Data
public class PlotListDo {
    /**
     * 关键字
     */
    private String keyword;
    /**
     * 小区id
     */
    private String plotId;
    /**
     * 区域id
     */
    private String districtId;
    /**
     * 商圈id
     */
    private String areaId;
    /**
     * 地铁线id
     */
    private String subwayLineId;
    /**
     * 地铁站id
     */
    private String subwayStationId;
    /**
     * 均价起始
     */
    private String beginPrice;
    /**
     * 均价结束
     */
    private String endPrice;
    /**
     * 楼龄
     */
    private String age;
    /**
     * 物业类型(1:住宅,2:别墅)
     */
    private String propertyTypeId;
    /**
     * 房源面积大小
     */
    private String houseAreaSize;
    /**
     * 电梯(0:无,1:有)
     */
    private String elevatorFlag;
    /**
     * 建筑类型(1：板楼，2：塔楼，3：板塔结合，4：砖楼5：其他)
     */
    private String buildingType;
    /**
     * 楼盘特色(楼盘标签1-近地铁，3-现房，4-精装修，5-花园洋房，6-复式，7-车位充足，8-低密度，9-500强房企，10-优质物业，11-购物方便，12-教育配套，13-医疗配套，14-换手率高，15-租金月供比高，16-五证齐全，17-人车分流)
     */
    private String buildingFeature;

    /**
     * 每页大小
     */
    private Integer size = 10;
    /**
     * 当前页数
     */
    private Integer pageNum = 1;
    /**
     * 维度 附近找房
     */
    private double lat;
    /**
     * 经度 附近找房
     */
    private double lon;
    /**
     * 楼盘标签
     */
    private String labelId;
}
