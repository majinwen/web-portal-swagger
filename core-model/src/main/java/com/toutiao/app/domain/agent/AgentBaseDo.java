package com.toutiao.app.domain.agent;

import lombok.Data;

/**
 * 经纪人返回实体类
 */

@Data
public class AgentBaseDo {


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
    /**
     * 经纪人融云信息
     */
    private String rcToken;
    /**
     * 名片
     */
    private String agentBusinessCard;
}
