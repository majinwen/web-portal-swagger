package com.toutiao.app.domain.newhouse;

import lombok.Data;

import java.util.List;

/**
 * @ClassName customConditionDetailsDos
 * @Author jiangweilong
 * @Date 2018/12/12 8:09 PM
 * @Description:
 **/

@Data
public class CustomConditionDetailsDomain {

    private List<CustomConditionDistrictDo> data;

//    private Integer districtId;
//
//    private String districtName;
//
//    private Double latitude;
//
//    private Double longitude;
//
//    private List<CustomConditionDetailsDo> data;
}
