package com.toutiao.app.domain.homepage;

import lombok.Data;

@Data
public class NearHouseSpecialPageDoQuery {
    /**
     * 小区id
     */
    private Integer plotId;

    /**
     * 当前页(默认1)
     */
    private Integer pageNum;
    /**
     * 每页条数(默认10)
     */
    private Integer size;
    /**
     * 城市id
     */
    private Integer cityId;
}
