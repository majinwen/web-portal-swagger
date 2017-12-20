package com.toutiao.web.dao.entity.robot;

import lombok.Data;

/**
 * 距离地铁距离相关类
 */
@Data
public class SubwayDistance {

    private Integer id;

    private double distance;

    private Integer lineId;

    private Integer stationId;

    private String stationName;

    private String subwayName;

}
