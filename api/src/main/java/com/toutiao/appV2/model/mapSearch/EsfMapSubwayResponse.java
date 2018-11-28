package com.toutiao.appV2.model.mapSearch;

import com.toutiao.app.domain.mapSearch.EsfMapSearchDo;
import com.toutiao.app.domain.mapSearch.EsfMapStationDo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by CuiShihao on 2018/11/27
 */
@Data
public class EsfMapSubwayResponse {

    @ApiModelProperty("当前可视界面描述")
    private String hit;

    @ApiModelProperty("所传地铁站周边小区聚合结果(当只查询地铁线时该值为空值)")
    private List<EsfMapStationDo> communityData;

    @ApiModelProperty("地铁线上的所有地铁站聚合结果")
    private List<EsfMapSearchDo> stationData;
}
