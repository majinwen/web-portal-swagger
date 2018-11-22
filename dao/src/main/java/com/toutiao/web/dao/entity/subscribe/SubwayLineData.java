package com.toutiao.web.dao.entity.subscribe;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 18710 on 2018/11/22.
 */
@Data
public class SubwayLineData implements Serializable {
    private Integer subwayid;

    private String name;

    private List<SubwayStationData> children;
}
