package com.toutiao.appV2.model.rent;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * RentDetailResponse
 */
@Validated
@Data
public class RentDetailResponse {
    @JsonProperty("agentBaseDo")
    @ApiModelProperty("经纪人信息")
    private AgentBaseDo agentBaseDo = null;

    @JsonProperty("agentBusinessCard")
    @ApiModelProperty("名片")
    private String agentBusinessCard = null;

    @JsonProperty("areaName")
    @ApiModelProperty("商圈名称")
    private String areaName = null;

    @JsonProperty("balcony")
    @ApiModelProperty("阳台")
    private Integer balcony = null;

    @JsonProperty("companyCard")
    @ApiModelProperty("经济公司营业执照")
    private String companyCard = null;

    @JsonProperty("createTime")
    @ApiModelProperty("时间")
    private String createTime = null;

    @JsonProperty("dataSourceName")
    @ApiModelProperty("房屋来源")
    private String dataSourceName = null;

    @JsonProperty("dataSourceSign")
    @ApiModelProperty("房源来源标志")
    private Integer dataSourceSign = null;

    @JsonProperty("demand")
    @ApiModelProperty("需求")
    private String demand = null;

    @JsonProperty("districtName")
    @ApiModelProperty("区域名称")
    private String districtName = null;

    @JsonProperty("floor")
    @ApiModelProperty("房源所在楼层数(地下室为负数)")
    private Integer floor = null;

    @JsonProperty("floorLevel")
    @ApiModelProperty("楼层级别")
    private String floorLevel = null;

    @JsonProperty("forward")
    @ApiModelProperty("朝向ID（1-东,2-西,3-南,4-北,5-东南,6-西南,7-东北,8-西北,9-东西,10-南北,11-其他）")
    private String forward = null;

    @JsonProperty("hall")
    @ApiModelProperty("几厅")
    private Integer hall = null;

    @JsonProperty("hasLift")
    @ApiModelProperty("电梯情况")
    private String hasLift = null;

    @JsonProperty("houseArea")
    @ApiModelProperty("房源面积")
    private Double houseArea = null;

    @JsonProperty("houseDesc")
    @ApiModelProperty("房源描述")
    private String houseDesc = null;

    @JsonProperty("houseId")
    @ApiModelProperty("出租房源Id")
    private String houseId = null;

    @JsonProperty("housePhoto")
    @Valid
    @ApiModelProperty("房源图片")
    private List<String> housePhoto = null;

    @JsonProperty("houseTitle")
    @ApiModelProperty("录入/导入房源标题")
    private String houseTitle = null;

    @JsonProperty("houseTitleImg")
    @ApiModelProperty("房源标题图")
    private String houseTitleImg = null;

    @JsonProperty("importTime")
    @ApiModelProperty("房源导入时间")
    private String importTime = null;

    @JsonProperty("isDefaultImage")
    @ApiModelProperty("是否显示默认图片标志")
    private Integer isDefaultImage = null;

    @JsonProperty("isFavorite")
    @ApiModelProperty("是否收藏")
    private Boolean isFavorite = null;

    @JsonProperty("isRecommend")
    @ApiModelProperty("是否是推荐")
    private Integer isRecommend = null;

    @JsonProperty("kitchen")
    @ApiModelProperty("几厨")
    private Integer kitchen = null;

    @JsonProperty("location")
    @ApiModelProperty("坐标")
    private String location = null;

    @JsonProperty("nearestSubway")
    @ApiModelProperty("最近的地铁")
    private String nearestSubway = null;

    @JsonProperty("payModeName")
    @ApiModelProperty("付款方式名称")
    private String payModeName = null;

    @JsonProperty("phone")
    @ApiModelProperty("电话")
    private String phone = null;

    @JsonProperty("publishTime")
    @ApiModelProperty("发布时间")
    private String publishTime = null;

    @JsonProperty("rentHousePrice")
    @ApiModelProperty("租金(元/月)")
    private Double rentHousePrice = null;

    @JsonProperty("rentHouseTagsName")
    @Valid
    @ApiModelProperty("出租房源标签名称")
    private List<String> rentHouseTagsName = null;

    @JsonProperty("rentHouseType")
    @ApiModelProperty("房源来源类型(录入/导入)")
    private Integer rentHouseType = null;

    @JsonProperty("rentSign")
    @ApiModelProperty("出租房源标志(普租/公寓)")
    private Integer rentSign = null;

    @JsonProperty("rentSignName")
    @ApiModelProperty("出租房源标志名称(出租/公寓)")
    private String rentSignName = null;

    @JsonProperty("rentType")
    @ApiModelProperty("出租类型")
    private Integer rentType = null;

    @JsonProperty("rentTypeName")
    @ApiModelProperty("租赁方式名称")
    private String rentTypeName = null;

    @JsonProperty("room")
    @ApiModelProperty("几室")
    private String room = null;

    @JsonProperty("supportingFacilities")
    @Valid
    @ApiModelProperty("配套设施(字符串数组)")
    private List<String> supportingFacilities = null;

    @JsonProperty("toilet")
    @ApiModelProperty("几卫")
    private Integer toilet = null;

    @JsonProperty("totalFloor")
    @ApiModelProperty("总楼层数")
    private Integer totalFloor = null;

    @JsonProperty("upStringTime")
    @ApiModelProperty("更新时间")
    private String upStringTime = null;

    @JsonProperty("zufangId")
    @ApiModelProperty("小区Id")
    private Integer zufangId = null;

    @JsonProperty("zufangName")
    @ApiModelProperty("小区名称")
    private String zufangName = null;

}

