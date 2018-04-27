package com.toutiao.app.api.chance.request.sellhouse;


import com.toutiao.app.api.chance.request.BaseQueryRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NearBySellHousesRequest extends BaseQueryRequest {

    /**
     * y坐标
     */
    @NotNull(message = "缺少坐标y")
    private Double lat;
    /**
     * x坐标
     */
    @NotNull(message = "缺少坐标x")
    private Double lon;
    /**
     * 附近距离
     */
    @NotNull(message = "缺少附近距离")
    private Integer distance;

}
