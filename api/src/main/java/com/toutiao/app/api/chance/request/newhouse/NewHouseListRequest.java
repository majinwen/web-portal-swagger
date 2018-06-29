package com.toutiao.app.api.chance.request.newhouse;

import com.toutiao.app.api.chance.request.BaseQueryRequest;
import lombok.Data;


@Data
public class NewHouseListRequest extends BaseQueryRequest {

    /**
     * 销售状态
     */
    private  Integer[] saleStatusId;

    /**
     * 环线
     */
    private String ringRoad;

    /**
     * 楼盘特色
     */
    private String buildingFeature;


}
