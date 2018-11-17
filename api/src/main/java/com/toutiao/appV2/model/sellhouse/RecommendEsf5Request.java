package com.toutiao.appV2.model.sellhouse;

import com.toutiao.appV2.model.BaseQueryRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "RecommendEsf5Request", description = "RecommendEsf5Request")
public class RecommendEsf5Request extends BaseQueryRequest {

    @ApiModelProperty(value = "区域", name = "districtIds")
    private Integer[] districtIds;

    @ApiModelProperty(value = "排序字段", name = "sortFile")
    private String sortFile = "recommendHouseTopicSort";

    @ApiModelProperty(value = "排序", name = "sort")
    private Integer sort = 1;
}
