package com.toutiao.app.api.chance.response.agent;

import lombok.Data;

@Data
public class AgentResponse {
    /**
     * 经纪人id
     */
    private String userId;
    /**
     * 经纪人名称
     */
    private String agentName;
    /**
     * 经纪公司
     */
    private String agentCompany;
    /**
     * 经纪人头像
     */
    private String headPhoto;
    /**
     * 经纪人虚拟电话
     */
    private String displayPhone;
}
