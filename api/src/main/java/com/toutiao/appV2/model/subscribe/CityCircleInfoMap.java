package com.toutiao.appV2.model.subscribe;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * Created by 18710 on 2018/11/24.
 */
@Data
public class CityCircleInfoMap {
    Integer total;
    @ApiModelProperty("城市商圈信息")
    Map<String,Object> CityCircleInfos;
}
