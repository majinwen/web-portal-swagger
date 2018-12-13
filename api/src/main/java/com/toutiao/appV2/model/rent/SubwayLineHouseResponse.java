package com.toutiao.appV2.model.rent;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.app.domain.rent.SubwayLineHouseDo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by CuiShihao on 2018/12/12
 */
@Data
public class SubwayLineHouseResponse {

    @JsonProperty("subwayLineHouseDos")
    @ApiModelProperty("地铁线房源统计")
    List<SubwayLineHouseDo> subwayLineHouseDos = null;
}
