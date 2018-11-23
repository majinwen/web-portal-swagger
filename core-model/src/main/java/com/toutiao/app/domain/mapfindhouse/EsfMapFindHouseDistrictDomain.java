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


    /**
     * 当前可视界面描述
     */
    private String hit;

    /**
     * 返回数据
     */
    private List<EsfMapFindHouseDistrictDo> data;
}
