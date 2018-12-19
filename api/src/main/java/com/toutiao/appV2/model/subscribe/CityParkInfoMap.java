package com.toutiao.appV2.model.subscribe;

import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * Created by 18710 on 2018/11/24.
 */
@Data
public class CityParkInfoMap {
    Integer total;
    @ApiModelProperty("城市所有区域信息")
    Map<String,Object> CityParkInfos;
}
