package com.toutiao.app.domain.mapSearch;

import lombok.Data;

import java.util.List;

/**
 * @ClassName EsfMapFindHouseBizcircleDomain
 * @Author jiangweilong
 * @Date 2018/11/22 9:25 PM
 * @Description:
 **/

@Data
public class EsfMapSearchBizcircleDomain {

    /**
     * 当前可视界面描述
     */
    private String hit;

    /**
     * 返回数据
     */
    private List<EsfMapSearchBizcircleDo> data;
}
