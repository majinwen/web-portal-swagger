package com.toutiao.app.domain.mapSearch;

import lombok.Data;

import java.util.List;

/**
 * @ClassName NewHouseMapFindHouseBuildDomain
 * @Author jiangweilong
 * @Date 2018/11/23 12:09 PM
 * @Description:
 **/

@Data
public class NewHouseMapSearchBuildDomain {


    /**
     * 返回结果
     */
    private List<NewHouseMapSearchBuildDo> buildDoList;

}
