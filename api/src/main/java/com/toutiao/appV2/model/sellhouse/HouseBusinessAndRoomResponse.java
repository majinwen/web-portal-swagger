package com.toutiao.appV2.model.sellhouse;

import com.toutiao.app.domain.sellhouse.HouseBusinessAndRoomDo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 商圈+户型
 */
@Data
@ApiModel(value = "HouseBusinessAndRoomResponse", description = "HouseBusinessAndRoomResponse")
public class HouseBusinessAndRoomResponse {

    @ApiModelProperty(value = "商圈+户型专题数据", name = "data")
    private List<HouseBusinessAndRoomDo> data;

    @ApiModelProperty(value = "商圈+户型均价", name = "averagePrice")
    private Double averagePrice;

    @ApiModelProperty(value = "房源总数", name = "totalCount")
    private long totalCount;
}
