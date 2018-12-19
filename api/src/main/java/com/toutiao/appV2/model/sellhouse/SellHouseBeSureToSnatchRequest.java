package com.toutiao.appV2.model.sellhouse;

import com.toutiao.appV2.model.BaseQueryRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "SellHouseBeSureToSnatchRequest", description = "SellHouseBeSureToSnatchRequest")
public class SellHouseBeSureToSnatchRequest extends BaseQueryRequest {

    @ApiModelProperty(value = "排序字段", name = "sortFile")
    private String sortFile = "updateTimeSort";

    @ApiModelProperty(value = "排序", name = "sort")
    private Integer sort = 1;

    @ApiModelProperty(value = "是否最新", name = "isNew")
    private Integer isNew;

    @ApiModelProperty(value = "区域Ids", name = "districtIds")
    private Integer[] districtIds;
}
