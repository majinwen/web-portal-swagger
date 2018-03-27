package com.toutiao.app.domain;

import lombok.Data;

@Data
public class MapInfo {
    /**
     * 序号
     */
    private Integer id;

    /**
     * 楼盘Id
     */
    private Integer newcode;

    /**
     * 地图信息结果
     */
    private Object dataInfo;

    private Object typeCount;

}