package com.toutiao.app.domain.newhouse;

import com.toutiao.app.domain.QueryDo;
import lombok.Data;

@Data
public class NewHouseListDo extends QueryDo {

    /**
     * 楼盘名称
     */

    private String buildingName;


    /**
     * 大楼标题图
     */
    private  String buildingTitleImg;


    /**
     * 销售状态
     */
     private  String saleStatusName;

    /**
     *区域名称
     */
    private  String  districtName;

    /**
     * 楼盘id
     */
    private Integer buildingNameId;

    /**
     * 附近地铁信息
     */
    private  String roundStation;

    /**
     * 交房日期
     */
    private  String deliverTime;


    /**
     * 车位配比
     */
     private String parkRadio;


    /**
     * y坐标
     */
    private  Double  lat;
    /**
     * X坐标
     */
    private  Double lon;

    /**
     * 距离
     */
    private  Integer distance;

    /**
     * 均价
     */
    private  String averagePrice;


    /**
     *  户型数量
     */
    private  long roomTotalCount;


    /**
     * 新房收藏数量
     */
    private Integer newHouseFavorite;


    /**
     * 总价
     */
    private Double  totalPrice;


    /**
     * 标签
     */
    private  String []  buildingTags;


}
