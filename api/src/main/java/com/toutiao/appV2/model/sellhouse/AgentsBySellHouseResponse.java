package com.toutiao.appV2.model.sellhouse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "AgentsBySellHouseResponse", description = "AgentsBySellHouseResponse")
public class AgentsBySellHouseResponse {

    @ApiModelProperty(value = "经纪人id", name = "agentId")
    private Integer agentId;

    @ApiModelProperty(value = "经纪人姓名", name = "agentName")
    private String agentName;

    @ApiModelProperty(value = "经纪人电话", name = "agentPhone")
    private String agentPhone;

    @ApiModelProperty(value = "经纪人头像", name = "agentHeadphoto")
    private String agentHeadphoto;

    @ApiModelProperty(value = "经纪公司", name = "company")
    private String company;

    @ApiModelProperty(value = "认领后房源id（不做业务逻辑使用）", name = "houseId")
    private String houseId;

    @ApiModelProperty(value = "二手房房源图片", name = "houseImg")
    private String[] houseImg;

    @ApiModelProperty(value = "房源标题", name = "houseTitle")
    private String houseTitle;

    @ApiModelProperty(value = "房源标题", name = "houseDesc")
    private String houseDesc;

    @ApiModelProperty(value = "房源标签", name = "houseTagsName")
    private String houseTagsName;

    @ApiModelProperty(value = "二手房房源标题图", name = "houseTitleImg")
    private String houseTitleImg;

    @ApiModelProperty(value = "二手房房源默认id", name = "corpHouseId")
    private Integer corpHouseId;
}
