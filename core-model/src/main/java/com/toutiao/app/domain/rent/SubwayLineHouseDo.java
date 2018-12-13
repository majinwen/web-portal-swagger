package com.toutiao.app.domain.rent;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by CuiShihao on 2018/12/12
 */
@Data
public class SubwayLineHouseDo {

    /***
     * 地铁线id
     */
    private Integer subwayLineId;

    /**
     * 地铁线名称
     */
    private String subwayLineName;

    /**
     * 小区数量
     */
    private Integer communityCount;

    /**
     * 房源数量
     */
    private Integer houseCount;
}
