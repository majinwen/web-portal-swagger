package com.toutiao.app.api.chance.request.newhouse;

import com.toutiao.app.api.chance.request.BaseQueryRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NewHouseDynamicRequest extends BaseQueryRequest {

    /**
     * 大楼id
     */
    @NotNull(message = "缺少大楼id")
    private Integer newCode;


}
