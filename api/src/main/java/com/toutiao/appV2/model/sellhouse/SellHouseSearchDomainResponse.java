package com.toutiao.appV2.model.sellhouse;

import com.toutiao.app.domain.sellhouse.SellHousesSearchDo;
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
}
