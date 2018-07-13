package com.toutiao.app.domain.homepage;

import lombok.Data;

import javax.validation.constraints.NotNull;

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
    /**
     * y坐标
     */
    @NotNull(message = "缺少坐标y")
    private Double lat;
    /**
     * x坐标
     */
    @NotNull(message = "缺少坐标x")
    private Double lon;
}
