package com.toutiao.app.domain.mapfindhouse;

import lombok.Data;

import java.util.List;

/**
 * @ClassName RentMapFindHouseDistrictDoQuery
 * @Author jiangweilong
 * @Date 2018/11/23 12:48 PM
 * @Description:
 **/

@Data
public class RentMapFindHouseBizcircleDomain {

    /**
     * 当前可视界面描述
     */
    private String hit;

    /**
     * 返回结果
     */
    private List<RentMapFindHouseBizcircleDo> data;
}
