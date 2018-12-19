package com.toutiao.web.dao.entity.subscribe;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by 18710 on 2018/11/22.
 */
@Data
public class SubwayStationData implements Serializable {
    private Integer stationid;

    private String station_name;

    private String coordX;

    private String coordY;
}
