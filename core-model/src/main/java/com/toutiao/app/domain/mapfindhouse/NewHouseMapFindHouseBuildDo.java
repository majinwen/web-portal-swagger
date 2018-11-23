package com.toutiao.app.domain.mapfindhouse;

import lombok.Data;

/**
 * @ClassName NewHouseMapFindHouseBuildDo
 * @Author jiangweilong
 * @Date 2018/11/23 11:06 AM
 * @Description:
 **/

@Data
public class NewHouseMapFindHouseBuildDo {

    /**
     * 区域id
     */
    private Integer districtId;

    /**
     * 区域名称
     */
    private String districtName;

    /**
     * 物业类型
     */
    private  String propertyType;

    /**
     * 楼盘名称
     */
    private  String buildingName;

    /**
     * 均价
     */
    private Double averagePrice;

    /**
     * 销售状态
     */
    private  String  saleStatusName;

    /**
     * 楼盘标题图
     */
    private  String buildingTitleImg;

    /**
     * 总价
     */
    private Double  totalPrice;

    /**
     * 标签
     */
    private  String [] buildingTags;

    /**
     * 建筑面积
     */
    private  Double buildingArea;

    /**
     * 坐标，纬度
     */
    private String latitude;

    /**
     * 坐标，经度
     */
    private String longitude;

    /**
     * 活动折扣 0:未参与,1:参与
     */
    private Integer isActive;

}
