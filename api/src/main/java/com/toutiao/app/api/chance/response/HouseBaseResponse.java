package com.toutiao.app.api.chance.response;

import com.toutiao.app.domain.agent.AgentBaseDo;
import lombok.Data;

/**
 * 房源详情基础返回实体类
 * 出售、出租房源
 */

@Data
public class HouseBaseResponse {


    /**
     * 房源id
     */
    private String houseId;
    /**
     * 楼盘id
     */
    private Integer buildingId;
    /**
     * 楼盘名称
     */
    private String buildingName;
    /**
     * 区域名称
     */
    private String districtName;
    /**
     * 商圈名称
     */
    private String areaName;
    /**
     * 最近地铁站信息
     */
    private String nearBySubwayDesc;
    /**
     * 房源所在楼层数(地下室为负数)
     */
    private Integer floor;
    /**
     * 房源所在层类型（高层/低层/中层）
     */
    private String floorName;
    /**
     * 朝向（1-东,2-西,3-南,4-北,5-东南,6-西南,7-东北,8-西北,9-东西,10-南北,11-其他）
     */
    private String forward;
    /**
     * 是否有电梯 (0-无，1-有)
     */
    private Integer hasElevator;
    /**
     * 标签
     */
    private String[] tags;
    /**
     * 房源面积
     */
    private String houseArea;
    /**
     * 几室
     */
    private String room;
    /**
     * 几厅
     */
    private Integer hall;
    /**
     * 房源标题
     */
    private String houseTitle;
    /**
     * 房源标题图
     */
    private String housePhotoTitle;
    /**
     * 房源图片
     */
    private String[] housePhoto;
    /**
     * 房源描述
     */
    private String houseDesc;
    /**
     * 坐标
     */
    private String location;
    /**
     * 经纪人信息
     */
    private AgentBaseDo agent;
    /**
     * 发布时间
     */
    private String publishTime;


}
