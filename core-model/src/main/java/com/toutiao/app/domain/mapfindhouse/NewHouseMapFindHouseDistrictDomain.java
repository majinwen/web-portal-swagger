package com.toutiao.app.domain.mapfindhouse;

import lombok.Data;

import java.util.List;

/**
 * @ClassName NewHouseMapFindHouseDistrictDomain
 * @Author jiangweilong
 * @Date 2018/11/22 9:58 PM
 * @Description:
 **/

@Data
public class NewHouseMapFindHouseDistrictDomain {

    /**
     * 当前可视界面描述
     */
    private String hit;

    /**
     * 返回数据
     */
    private List<NewHouseMapFindHouseDistrictDo> data;
}
