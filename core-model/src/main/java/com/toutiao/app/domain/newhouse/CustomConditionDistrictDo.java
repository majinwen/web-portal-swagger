package com.toutiao.app.domain.newhouse;

import lombok.Data;

import java.util.List;

/**
 * @ClassName CustomConditionDistrictDo
 * @Author jiangweilong
 * @Date 2018/12/13 1:36 PM
 * @Description:
 **/

@Data
public class CustomConditionDistrictDo {

    private Integer districtId;

    private String districtName;

    private Double latitude;

    private Double longitude;

    private List<CustomConditionDetailsDo> data;

}
