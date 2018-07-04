package com.toutiao.app.domain.sellhouse;

import lombok.Data;

@Data
public class SellHouseBeSureToSnatchDoQuery {

    private  String sortFile;

    private  String  sort;
    /**
     * 起始价格
     */
    private  double beginPrice;

    /**
     * 结束价格
     */
    private double endPrice;


    /**
     * 区域
     */
    private Integer districtId;

    private  Integer pageNum;

    private  Integer pageSize;
}
