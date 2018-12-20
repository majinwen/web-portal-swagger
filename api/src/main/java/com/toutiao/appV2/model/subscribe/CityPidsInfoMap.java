package com.toutiao.appV2.model.subscribe;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * Created by 18710 on 2018/11/24.
 */
@Data
public class CityPidsInfoMap {
    Integer total;
    @ApiModelProperty("城市所有广告位信息")
    Map<String,Object> CityPidsInfos;
}
