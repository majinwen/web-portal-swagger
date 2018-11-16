package com.toutiao.appV2.model.sellhouse;

import com.toutiao.appV2.model.BaseQueryRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "MustBuyShellHouseRequest", description = "MustBuyShellHouseRequest")
public class MustBuyShellHouseRequest extends BaseQueryRequest {

    @ApiModelProperty(value = "区域Ids", name = "districtIds")
    private Integer[] districtIds;

    @ApiModelProperty(value = "排序方式(0-更新时间降序, 1-总价升, 2-总价降, 3-降价房-涨幅升, 4-降价房-涨幅降, 3-捡漏房-面积升, 4-捡漏房-面积降)", name = "sort")
    private Integer sort = 0;
}
