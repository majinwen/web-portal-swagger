package com.toutiao.appV2.model.subscribe;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * Created by 18710 on 2018/11/24.
 */
@Data
public class CityConditionInfoMap {
    Integer total;
    @ApiModelProperty("searchConditionData搜索条件（new新房，rent租房，community小区，second二手房）")
    Map<String,Object> CityConditionInfos;
}
