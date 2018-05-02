package com.toutiao.app.api.chance.response.newhouse;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class NewHouseListResponse  {


    /**
     * 最小面积
     */
    private Double houseMinArea;

    /**
     * 最大面积
     */
    private  Double houseMaxArea;

    /**
     * 楼盘名称
     */
   @JSONField(name="buildingName")
    private String buildingName;

    /**
     * 楼盘id
     */

    private  Integer buildingNameId;

    /**
     * 区域名字
     */
    @JSONField(name="districtName")
    private  String  districtName;

    /**
     * 区域id
     */
    private Integer districtId;

    /**
     * 地铁信息
     */
    private  String roundStation;

    /**
     * 最近交房
     */
    private  String deliverTime;


    /**
     * 车位配比
     */
    @JSONField(name="parkRatio")
    private String parkRadio;

    /**
     * 均价
     */
    @JSONField(name="averagePrice")
    private  String averagePrice;

    /**
     * 销售状态
     */
    private  String  saleStatusName;

    /**
     * 大楼标题图
     */
    private  String buildingTitleImg;


    private  long roomTotalCount;

    /**
     * 新房收藏数量
     */
    private Integer newHouseFavorite;


    /**
     *总价
     */
    @JSONField(name="totalPrice")
    private Double  totalPrice;


    /**
     * 类别
     */
    private  String propertyType;
}
