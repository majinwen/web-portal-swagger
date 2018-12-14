package com.toutiao.appV2.model.rent;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.app.domain.rent.RentCustomConditionDo;
import com.toutiao.app.domain.rent.RentCustomDo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by CuiShihao on 2018/12/12
 */
@Data
public class RentCustomConditionResponse {


    @JsonProperty("rentCustomDos")
    @ApiModelProperty("房源统计信息")
    /**
     * 房源统计信息
     */
    private List<RentCustomDo> rentCustomDos = null;
}
