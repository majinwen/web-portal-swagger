package com.toutiao.app.domain.mapfindhouse;

import lombok.Data;

import java.util.List;

/**
 * @ClassName EsfMapFindHouseDistrictDomain
 * @Author jiangweilong
 * @Date 2018/11/22 4:38 PM
 * @Description:
 **/

@Data
public class EsfMapFindHouseDistrictDomain {


    private String hit;

    List<EsfMapFindHouseDistrictDo> data;
}
