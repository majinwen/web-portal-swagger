package com.toutiao.appV2.model.sellhouse;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "SellHouseSearchDomainResponse", description = "SellHouseSearchDomainResponse")
public class SellHouseSearchDomainResponse {

    @ApiModelProperty(value = "搜索二手房列表", name = "data")
    private List<SellHousesSearchDo> data;

    @ApiModelProperty(value = "总条数", name = "totalNum")
    private Integer totalNum;

    @ApiModelProperty(value = "是否为猜你喜欢的数据", name = "isGuess")
    private Integer isGuess;
}
