package com.toutiao.app.domain.rent;

import com.toutiao.app.domain.mapSearch.SubwayStationDo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by CuiShihao on 2018/12/13
 */
@Data
public class RentCustomDo {

    /***
     * id
     */
    @ApiModelProperty(name = "id", value = "id")
    private Integer id;

    /**
     * 名称
     */
    @ApiModelProperty(name = "name", value = "名称")
    private String name;

    /**
     * 房源统计
     */
    @ApiModelProperty(name = "rentCustomConditionDos", value = "房源分布统计")
    private List<RentCustomConditionDo> rentCustomConditionDos = null;

    @ApiModelProperty(name = "subwayStations", value = "地铁站信息")
    private List<SubwayStationDo> subwayStations = null;
}
