package com.toutiao.app.domain.homepage;

import lombok.Data;

@Data
public class HomePageNewHouseDo {

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
}
