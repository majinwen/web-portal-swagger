package com.toutiao.app.api.chance.response.newhouse;

import lombok.Data;

@Data
public class NewHosueListResponse {

    /**
     * 最小面积
     */
    private Double house_min_area;

    /**
     * 最大面积
     */
    private  Double house_max_area;

    /**
     * 楼盘名称
     */
    private String building_name;

    /**
     * 楼盘id
     */
     private  Integer building_name_id;

    /**
     * 区域名字
     */
    private  String  district_name;

    /**
     * 区域id
     */
    private Integer district_id;

    /**
     * 地铁信息
     */
    private  String roundstation;

    /**
     * 最近交房
     */

    private  String deliver_time;


    /**
     * 车位配比
     */
    private String park_radio;

    /**
     * 均价
     */
    private  String average_price;

    /**
     * 销售状态
     */
    private  String  sale_status_name;

    /**
     * 大楼标题图
     */
    private  String building_title_img;


    private  long roomTotalCount;






}
