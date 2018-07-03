package com.toutiao.app.api.chance.request.homepage;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NearHouseSpecialPageRequest {
    /**
     * 小区id
     */
    @NotNull(message = "小区id不能为空")
    private Integer plotId;
    /**
     * 当前页(默认1)
     */
    private Integer pageNum = 1;
    /**
     * 每页条数(默认10)
     */
    private Integer size = 10;
    /**
     * 城市id
     */
    private Integer cityId =12;
}
