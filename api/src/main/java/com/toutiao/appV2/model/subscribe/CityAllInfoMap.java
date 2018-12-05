package com.toutiao.appV2.model.subscribe;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * Created by 18710 on 2018/11/22.
 */
@Data
public class CityAllInfoMap {
    String version;
    @ApiModelProperty("searchConditionData搜索条件（new新房，rent租房，community小区，second二手房），pidsList广告位ID（homeSearchPop搜索引导页广告位），circleDataList区域信息，subwayDataList地铁线信息")
    Map<String, Object> CityAllInfos;
}
