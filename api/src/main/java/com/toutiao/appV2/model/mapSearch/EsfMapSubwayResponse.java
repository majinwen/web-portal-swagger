package com.toutiao.appV2.model.mapSearch;

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

    @ApiModelProperty("地铁站聚合结果")
    private List<EsfMapStationDo> data;
}
