package com.toutiao.app.domain.Rent;

import lombok.Data;

@Data
public class RentAgentDo {

    /**
     * 经纪人id
     */
    private Integer agent_id;
    /**
     * 经纪人姓名
     */
    private String agent_name;
    /**
     * 经纪人电话
     */
    private String agent_phone;
    /**
     * 经纪人头像
     */
    private String agent_headphoto;
    /**
     * 经纪公司
     */
    private String of_company;
    /**
     * 认领后房源id（不做业务逻辑使用）
     */
    private String houseId;
    /**
     * 房源图片
     */
    private String[] house_img;
    /**
     * 房源标题
     */
    private String house_title;
    /**
     * 房源描述
     */
    private String house_desc;
    /**
     * 房源标签
     */
    private String[] house_tags_name;
    /**
     * 房源标题图
     */
    private String house_title_img;
    /**
     * 房源默认id
     */
    private String corp_house_id;
}
