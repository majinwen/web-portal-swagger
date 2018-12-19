package com.toutiao.app.domain.mapSearch;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by CuiShihao on 2018/11/27
 */
@Data
public class EsfMapStationDomain {

    @ApiModelProperty("当前可视界面描述")
    private String hit;

    @ApiModelProperty("所传地铁站周边小区聚合结果")
    private List<EsfMapStationDo> communityData;

    @ApiModelProperty("地铁线上的所有地铁站聚合结果")
    private List<EsfMapSearchDo> stationData;

}
