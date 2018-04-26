package com.toutiao.app.domain.rent;

import lombok.Data;

@Data
public class RentAgentDo {

    /**
     * 经纪人id
     */
    private Integer agentId;
    /**
     * 经纪人姓名
     */
    private String agentName;
    /**
     * 经纪人电话
     */
    private String agentPhone;
    /**
     * 经纪人头像
     */
    private String agentHeadphoto;
    /**
     * 经纪公司
     */
    private String ofCompany;
    /**
     * 认领后房源id（不做业务逻辑使用）
     */
    private String houseId;
    /**
     * 房源图片
     */
    private String[] houseImg;
    /**
     * 房源标题
     */
    private String houseTitle;
    /**
     * 房源描述
     */
    private String houseDesc;
    /**
     * 房源标签
     */
    private String[] houseTagsName;
    /**
     * 房源标题图
     */
    private String houseTitleImg;
    /**
     * 房源默认id
     */
    private String corpHouseId;
}
