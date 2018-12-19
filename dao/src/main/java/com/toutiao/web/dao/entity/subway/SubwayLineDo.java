package com.toutiao.web.dao.entity.subway;

import lombok.Data;

/**
 * Created by CuiShihao on 2018/12/13
 */
@Data
public class SubwayLineDo {

    /**
     * 地铁线id
     */
    private Integer lineId;

    /**
     * 地铁线名称
     */
    private String subwayName;

    /**
     * 城市id
     */
    private Integer cityId;
}
