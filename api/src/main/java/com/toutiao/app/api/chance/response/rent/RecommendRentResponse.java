package com.toutiao.app.api.chance.response.rent;

import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class RecommendRentResponse {


    /**
     * 出租房源Id
     */
    @ApiModelProperty("出租房源Id")
    private String houseId;

    /**
     * 租金(元/月)
     */
    @ChangeName("rentPrice")
    @ApiModelProperty("租金(元/月)")
    private Double rentHousePrice;

    /**
     * 租赁方式名称
     */
    @ApiModelProperty("租赁方式名称")
    private String rentTypeName;
    /**
     * 房源面积
     */
    @ApiModelProperty("房源面积")
    private Double houseArea;
    /**
     * 几室
     */
    @ApiModelProperty("几室")
    private String room;
    /**
     * 几厅
     */
    @ApiModelProperty("几厅")
    private Integer hall;
    /**
     * 朝向（1-东,2-西,3-南,4-北,5-东南,6-西南,7-东北,8-西北,9-东西,10-南北,11-其他）
     */
    @ApiModelProperty("朝向（1-东,2-西,3-南,4-北,5-东南,6-西南,7-东北,8-西北,9-东西,10-南北,11-其他）")
    private String forward;

    /**
     * 区域名称
     */
    @ApiModelProperty("区域名称")
    private String districtName;

    /**
     * 商圈名称
     */
    @ApiModelProperty("商圈名称")
    private String areaName;

    /**
     * 小区ID
     */
    @ChangeName("buildingId")
    @ApiModelProperty("小区ID")
    private Integer zufangId;

    /**
     * 小区名称
     */
    @ChangeName("buildingName")
    @ApiModelProperty("小区名称")
    private String zufangName;

    /**
     * 出租房源标签名称
     */
    @ChangeName("tags")
    @ApiModelProperty("出租房源标签名称")
    private String[] rentHouseTagsName;

    /**
     * 推优查询id
     */
    @ApiModelProperty("推优查询id")
    private String uid;

    /**
     * 录入/导入房源标题
     */
    @ApiModelProperty("录入/导入房源标题")
    private String houseTitle;

    /**
     * 房源标题图
     */
    @ChangeName("housePhotoTitle")
    @ApiModelProperty("房源标题图")
    private String houseTitleImg;

    @ChangeName("agent")
    private AgentBaseDo agentBaseDo;

    /**
     * 出租类型
     */
    @ApiModelProperty("出租类型")
    private  Integer rentType;


    @ChangeName("nearBySubway")
    @ApiModelProperty("最近的地铁")
    private String nearestSubway;

    private String[] housePhoto;

    /**
     * 房源描述
     */
    @ApiModelProperty("房源描述")
    private String houseDesc;

}
