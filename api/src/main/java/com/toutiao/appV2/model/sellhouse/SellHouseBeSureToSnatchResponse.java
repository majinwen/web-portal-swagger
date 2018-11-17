package com.toutiao.appV2.model.sellhouse;

import com.toutiao.app.domain.sellhouse.SellHouseBeSureToSnatchDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "SellHouseBeSureToSnatchResponse", description = "SellHouseBeSureToSnatchResponse")
public class SellHouseBeSureToSnatchResponse {

    @ApiModelProperty(value = "逢抢必出列表", name = "data")
    List<SellHouseBeSureToSnatchDo> data;

    @ApiModelProperty(value = "订阅Id", name = "subscribeId")
    private Integer subscribeId;

    @ApiModelProperty(value = "总条数", name = "totalCount")
    private long totalCount;
}
