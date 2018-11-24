package com.toutiao.appV2.model.subscribe;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.dao.entity.subscribe.SubwayLineData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by 18710 on 2018/11/24.
 */
@Data
public class CitySubwayInfoMap {
    Integer total;
    @ApiModelProperty("城市所有地铁信息")
    List<SubwayLineData> CitySubwayInfos;
}
