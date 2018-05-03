package com.toutiao.app.api.chance.request.agent;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AgentRequest {
    /**
     * 经纪人id
     */
    @NotNull(message = "缺少经纪人id")
    private String userId;
}
