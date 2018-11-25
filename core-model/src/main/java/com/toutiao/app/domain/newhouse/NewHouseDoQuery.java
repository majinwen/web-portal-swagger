package com.toutiao.app.domain.newhouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.app.domain.QueryDo;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class NewHouseDoQuery extends QueryDo {


    /**
     * 销售状态
     */
    private  Integer [] saleStatusId;

    /**
     * 环线
     */
    private String ringRoad;

    /**
     * 楼盘特色
     */
    private String buildingFeature;

    /**
     * 起始总价
     */
    private double beginTotalPrice;

    /**
     * 结束总价
     */
    private double endTotalPrice;

}
