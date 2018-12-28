package com.toutiao.app.domain.sellhouse;

import lombok.Data;

@Data
public class SellHouseBeSureToSnatchDoQuery {

    private String sortFile = "updateTimeSort";

    private Integer sort = 1;
    /**
     * 起始价格
     */
    private double beginPrice;

    /**
     * 结束价格
     */
    private double endPrice;

    /**
     * 区域Ids
     */
    private Integer[] districtIds;

    /**
     * 商圈Ids
     */
    private Integer[] areaId;

    private Integer pageNum;

    private Integer pageSize;

    private Integer isNew;
}
