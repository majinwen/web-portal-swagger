package com.toutiao.app.domain.newhouse;

import lombok.Data;

@Data
public class NewHouseLayoutDo {

    /**
     *
     *销售状态(0-售完,1-在售,2-不在售,3-出租,4-租售,5-待售)
     */
    private  Integer is_sale;

    /**
     * 楼盘id
     */
    private  Integer building_id;

    /**
     * 户型id
     */
    private  Integer layout_id;


    /**
     * 厅
     */
    private  Integer hall;

    /**
     * 建筑面积
     */
    private  Double building_area;

    /**
     * 参考均价
     */
    private  Double reference_price;

    /**
     * 户型标题
     */
     private  String layout_title;

    /**
     * 户型图片
     */
    private  String layout_img;

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
    private  Double reference_total_price;

    /**
     * 销售面积
     */
    private  Double sale_area;

    /**
     * 是否推荐
     */
    private  Integer is_recommend;

    /**
     * 户型描述/户型解析
     */
    private  String layout_desc;

    /**
     * 居住面积
     */
    private  Double living_area;

    /**
     * 厨房
     */
    private  Integer kitchen;

}
