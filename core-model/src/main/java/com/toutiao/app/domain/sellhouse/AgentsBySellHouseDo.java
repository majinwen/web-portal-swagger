package com.toutiao.app.domain.sellhouse;

import lombok.Data;

@Data
public class AgentsBySellHouseDo {


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
    private String company;
    /**
     * 认领后房源id（不做业务逻辑使用）
     */
    private String houseId;
    /**
     * 二手房房源图片
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
    private String houseTagsName;
    /**
     * 二手房房源标题图
     */
    private String houseTitleImg;
    /**
     * 二手房房源默认id
     */
    private Integer corpHouseId;

}
