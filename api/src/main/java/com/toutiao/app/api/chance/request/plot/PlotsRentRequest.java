package com.toutiao.app.api.chance.request.plot;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PlotsRentRequest {
    /**
     * 小区id
     */
    @NotNull(message = "缺少小区Id")
    private Integer plotId;
    /**
     * 出租类型(整租:1/合租:2)
     */
    private Integer rentType;
    /**
     * 当前页
     */
    private Integer pageNum=1;
}
