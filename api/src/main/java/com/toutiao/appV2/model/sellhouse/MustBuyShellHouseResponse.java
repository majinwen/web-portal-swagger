package com.toutiao.appV2.model.sellhouse;

import com.toutiao.app.domain.sellhouse.MustBuyShellHouseDo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "MustBuyShellHouseResponse", description = "MustBuyShellHouseResponse")
public class MustBuyShellHouseResponse {

    @ApiModelProperty(value = "降价房数据", name = "data")
    private List<MustBuyShellHouseDo> data;

    @ApiModelProperty(value = "是否被订阅", name = "subscribeId")
    private Integer subscribeId;

    @ApiModelProperty(value = "总条数", name = "totalCount")
    private long totalCount;
}
