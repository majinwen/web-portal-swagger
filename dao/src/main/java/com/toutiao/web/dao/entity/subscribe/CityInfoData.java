package com.toutiao.web.dao.entity.subscribe;

import lombok.Data;

import java.util.List;

/**
 * Created by 18710 on 2018/11/22.
 */
@Data
public class CityInfoData {
    private Integer cityId;

    private String adInfo;

    private String districtInfo;

    private String parkInfo;

    private List<CityConditionData> cityConditionDataList;
}
