package com.toutiao.app.domain.houseCount;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by CuiShihao on 2018/12/6
 */
@Data
public class HouseCountDomain {

    @ApiModelProperty(value = "二手房关键字搜索房源数量", name = "esfHouseCount")
    private long esfHouseCount;

    @ApiModelProperty(value = "新房关键字搜索房源数量", name = "newHouseCount")
    private long newHouseCount;

    @ApiModelProperty(value = "租房关键字搜索房源数量", name = "rentHouseCount")
    private long rentHouseCount;

    @ApiModelProperty(value = "小区关键字搜索房源数量", name = "plotHouseCount")
    private long plotHouseCount;
}
