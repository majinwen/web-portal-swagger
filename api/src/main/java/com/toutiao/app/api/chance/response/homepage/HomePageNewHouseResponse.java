package com.toutiao.app.api.chance.response.homepage;

import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

@Data
public class HomePageNewHouseResponse {

    /**
     * 楼盘名称
     */

    private  String   buildingName;

    /**
     * 总价
     */
    private Double  totalPrice;

    /**
     * 区域
     */

    private  String districtName;

    /**
     * 最小面积
     */
    private  Double houseMinArea;


    /**
     * 最大面积
     */
    private  Double houseMaxArea;


    /**
     * 销售状态
     */
    private  String  saleStatusName;


    /**
     * 标签
     */
    private  String []  buildingTags;


    /**
     * 标题图
     */

    private  String  buildingTitleImg;

    /**
     * 均价
     */
    private  String averagePrice;

    /**
     * 类别
     */
    private  String propertyType;


    /**
     * 大楼id
     */
    private  Integer buildingNameId;

    /**
     *  最近交房
     */
    private  String deliverTime;

    /**
     * 环路
     */
    @ChangeName("ringRoad")
    private  String ringRoadName;

}
