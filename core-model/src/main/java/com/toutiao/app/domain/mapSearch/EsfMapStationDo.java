package com.toutiao.app.domain.mapSearch;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by CuiShihao on 2018/11/27
 */
@Data
public class EsfMapStationDo {

    @ApiModelProperty("地铁站id")
    private Integer stationId;

    @ApiModelProperty("地铁站名称")
    private String stationName;

    @ApiModelProperty("小区信息")
    private List<EsfMapSearchDo> data;
}
