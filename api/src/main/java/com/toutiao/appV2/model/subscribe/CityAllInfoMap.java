package com.toutiao.appV2.model.subscribe;

import lombok.Data;

import java.util.Map;

/**
 * Created by 18710 on 2018/11/22.
 */
@Data
public class CityAllInfoMap {
    Integer total;
    Map<String, Object> CityAllInfos;
}
