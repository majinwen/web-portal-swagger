package com.toutiao.app.domain.mapSearch;

import lombok.Data;

import java.util.List;

/**
 * @ClassName EsfMapFindHouseCommunityDomain
 * @Author jiangweilong
 * @Date 2018/11/22 9:34 PM
 * @Description:
 **/

@Data
public class EsfMapSearchDomain {

    /**
     * 当前可视界面描述
     */
    private String hit;

    /**
     * 返回数据
     */
    private List<EsfMapSearchDo> data;
}
