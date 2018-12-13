package com.toutiao.app.domain.sellhouse;

import com.toutiao.app.domain.QueryDo;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NearBySellHouseQueryDo extends QueryDo {
    /**
     * 区域Ids
     */
    private Integer[] districtIds;
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
