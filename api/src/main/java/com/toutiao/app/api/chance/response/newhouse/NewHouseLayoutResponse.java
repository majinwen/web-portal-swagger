package com.toutiao.app.api.chance.response.newhouse;

import lombok.Data;

@Data
public class NewHouseLayoutResponse {

    /**
     *
     *销售状态(0-售完,1-在售,2-不在售,3-出租,4-租售,5-待售)
     */
    private  Integer isSale;

    /**
     * 楼盘id
     */
    private  Integer buildingId;

    /**
     * 户型id
     */
    private  Integer layoutId;


    /**
     * 厅
     */
    private  Integer hall;

    /**
     * 建筑面积
     */
    private  Double buildingArea;

    /**
     * 参考均价
     */
    private  Double referencePrice;

    /**
     * 户型标题
     */
    private  String layoutTitle;

    /**
     * 户型图片
     */
    private  String layoutImg;

    /**
     * 室
     */
    private  Integer room;

    /**
     * 厕所
     */
    private  Integer toilet;

    /**
     * 参考总价
     */
    private  Double referenceTotalPrice;

    /**
     * 销售面积
     */
    private  Double saleArea;

    /**
     * 是否推荐
     */
    private  Integer isRecommend;

    /**
     * 户型描述/户型解析
     */
    private  String layoutDesc;

    /**
     * 居住面积
     */
    private  Double livingArea;

    /**
     * 厨房
     */
    private  Integer kitchen;


}
