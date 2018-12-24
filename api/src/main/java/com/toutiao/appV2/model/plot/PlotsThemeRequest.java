package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * Created by 18710 on 2018/12/24.
 */
@Data
public class PlotsThemeRequest {
    /**
     * 最近的公园
     */
    @JsonProperty("nearestPark")
    @ApiParam("最近的公园")
    private String nearestPark;

    /**
     * 标签Id(2-首次置业，3-改善生活，4-豪宅，5-别墅，6-公园名称)
     */
    @JsonProperty("recommendBuildTagsId")
    @ApiParam("标签Id(2-首次置业，3-改善生活，4-豪宅，5-别墅，6-公园名称)")
    private Integer recommendBuildTagsId;

    /**
     * 区域
     */
    @JsonProperty("districtIds")
    @ApiParam("区域ids")
    private Integer[] districtIds;
}
