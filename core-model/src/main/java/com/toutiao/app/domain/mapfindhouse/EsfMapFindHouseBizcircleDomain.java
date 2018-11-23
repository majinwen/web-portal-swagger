package com.toutiao.app.domain.mapfindhouse;

import lombok.Data;

import java.util.List;

/**
 * @ClassName EsfMapFindHouseBizcircleDomain
 * @Author jiangweilong
 * @Date 2018/11/22 9:25 PM
 * @Description:
 **/

@Data
public class EsfMapFindHouseBizcircleDomain {

    /**
     * 当前可视界面描述
     */
    private String hit;

    /**
     * 返回数据
     */
    private List<EsfMapFindHouseBizcircleDo> data;
}
