package com.toutiao.appV2.model.rent;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by CuiShihao on 2018/11/16
 */
@Data
public class NearRentHouseResponse {

    @ApiModelProperty("出租房源详情列表")
    private List<RentDetailFewResponse> data;

    @ApiModelProperty("列表的size")
    private Integer totalNum;
}
