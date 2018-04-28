package com.toutiao.app.domain.plot;

import com.toutiao.app.domain.QueryDo;
import lombok.Data;

@Data
public class NearbyPlotsDoQuery extends QueryDo{

    /**
     * 维度 附近找房
     */
    private Double lat;
    /**
     * 经度 附近找房
     */
    private Double lon;
    /**
     * 附近列表默认距离
     */
    private String distance;

}
