package com.toutiao.app.api.chance.request.newhouse;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NewHouseDynamicRequest {

    /**
     * 大楼id
     */
    @NotNull(message = "缺少大楼id")
    private Integer newCode;

    /**
     * 页码
     */
    @NotNull(message = "缺少页码")
    private Integer pageNum = 1;

    /**
     * 每页数量
     */
    private Integer pageSize=10;

}
