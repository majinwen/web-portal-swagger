package com.toutiao.app.domain.mapSearch;

import lombok.Data;

/**
 * Created by CuiShihao on 2018/11/26
 */
@Data
public class EsfMapCommunityDo {

    /**
     * 社区名称
     */
    private String ploatName;

    /**
     * 社区ID
     */
    private Integer newcode;

    /**
     * 坐标 纬度
     */
    private Double plotLatitude;

    /**
     * 坐标 经度
     */
    private Double plotLongitude;

    /**
     * 房源数量
     */
    private Integer count;

    /**
     * 均价
     */
    private Double communityAvgPrice;
}
