package com.toutiao.appV2.model.sellhouse;

import com.toutiao.appV2.model.BaseQueryRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 商圈+户型
 */
@Data
@ApiModel(value = "HouseBusinessAndRoomRequest", description = "HouseBusinessAndRoomRequest")
public class HouseBusinessAndRoomRequest extends BaseQueryRequest {

    @ApiModelProperty(value = "商圈Id", name = "areaId")
    @NotNull(message = "缺少商圈Id")
    private Integer[] areaId;

    @ApiModelProperty(value = "商圈名称", name = "areaName")
    @NotNull(message = "缺少商圈名称")
    private String areaName;

    @ApiModelProperty(value = "详情页房源编号", name = "houseId")
    private String houseId;

    @ApiModelProperty(value = "厅", name = "hall")
    @NotNull(message = "缺少厅")
    private Integer hall;
}
