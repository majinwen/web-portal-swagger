package com.toutiao.app.api.chance.response.rent;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RentAgentResponse {
    /**
     * 经纪人id
     */
    @ApiModelProperty("经纪人id")
    private Integer agentId;
    /**
     * 经纪人姓名
     */
    @ApiModelProperty("经纪人姓名")
    private String agentName;
    /**
     * 经纪人电话
     */
    @ApiModelProperty("经纪人电话")
    private String agentPhone;
    /**
     * 经纪人头像
     */
    @ApiModelProperty("经纪人头像")
    private String agentHeadphoto;
    /**
     * 经纪公司
     */
    @ApiModelProperty("经纪公司")
    private String ofCompany;
    /**
     * 认领后房源id（不做业务逻辑使用）
     */
    @ApiModelProperty("认领后房源id（不做业务逻辑使用）")
    private String houseId;
    /**
     * 房源图片
     */
    @ApiModelProperty("房源图片")
    private String[] houseImg;
    /**
     * 房源标题
     */
    @ApiModelProperty("房源标题")
    private String houseTitle;
    /**
     * 房源描述
     */
    @ApiModelProperty("房源描述")
    private String houseDesc;
    /**
     * 房源标签
     */
    @ApiModelProperty("房源标签")
    private String[] houseTagsName;
    /**
     * 房源标题图
     */
    @ApiModelProperty("房源标题图")
    private String houseTitleImg;
    /**
     * 房源默认id
     */
    @ApiModelProperty("房源默认id")
    private String corpHouseId;
}
