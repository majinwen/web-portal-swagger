package com.toutiao.app.domain.agent;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 经纪人返回实体类
 */

@Data
public class AgentBaseDo {


    /**
     * 经纪人id
     */
    @ApiModelProperty(value = "经纪人id", name = "userId")
    private String userId;
    /**
     * 经纪人名称
     */
    @ApiModelProperty(value = "经纪人名称", name = "agentName")
    private String agentName;
    /**
     * 经纪公司
     */
    @ApiModelProperty(value = "经纪公司", name = "agentCompany")
    private String agentCompany;
    /**
     * 经纪人头像
     */
    @ApiModelProperty(value = "经纪人头像", name = "headPhoto")
    private String headPhoto;
    /**
     * 经纪人虚拟电话
     */
    @ApiModelProperty(value = "经纪人虚拟电话", name = "displayPhone")
    private String displayPhone;
    /**
     * 经纪人融云信息
     */
    @ApiModelProperty(value = "经纪人融云信息", name = "rcToken")
    private String rcToken;
    /**
     * 名片
     */
    @ApiModelProperty(value = "名片", name = "agentBusinessCard")
    private String agentBusinessCard;

}
