package com.toutiao.appV2.model.sellhouse;

import com.toutiao.app.domain.sellhouse.NearBySellHousesDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "NearBySellHouseDomainResponse", description = "NearBySellHouseDomainResponse")
public class NearBySellHouseDomainResponse {

    @ApiModelProperty(value = "二手房列表", name = "nearBySellHousesDos")
    List<NearBySellHousesDo> nearBySellHousesDos;

    @ApiModelProperty(value = "总条数", name = "totalCount")
    private long totalCount;
}
