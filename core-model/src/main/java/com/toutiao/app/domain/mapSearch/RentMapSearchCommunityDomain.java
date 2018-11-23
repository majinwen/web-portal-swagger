package com.toutiao.app.domain.mapSearch;

import lombok.Data;

import java.util.List;

/**
 * @ClassName RentMapFindHouseCommunityDomain
 * @Author jiangweilong
 * @Date 2018/11/23 1:24 PM
 * @Description:
 **/

@Data
public class RentMapSearchCommunityDomain {

    /**
     * 当前可视界面描述
     */
    private String hit;

    /**
     * 返回结果
     */
    private List<RentMapSearchCommunityDo> data;
}
