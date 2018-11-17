package com.toutiao.app.domain.favorite.rent;

import com.toutiao.web.common.constant.city.CityEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class RentFavoriteDo {

    /**
     * 租房收藏id
     */
    @ApiModelProperty(value = "租房收藏id", name = "id")
    private Integer id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", name = "userId")
    private Integer userId;

    /**
     * 租房id
     */
    @ApiModelProperty(value = "租房id", name = "houseId")
    private String houseId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createTime")
    private Date createTime;

    /**
     * 是否删除(0-未删除，1-已删除)
     */
    @ApiModelProperty(value = "是否删除(0-未删除，1-已删除)", name = "isDel")
    private Short isDel;

    /**
     * 租金
     */
    @ApiModelProperty(value = "租金", name = "rentPrice")
    private  Double rentPrice;

    /**
     * 上下架状态
     */
    @ApiModelProperty(value = "上下架状态", name = "status")
    private  Integer status;

    /**
     * 出租类型
     */
    @ApiModelProperty(value = "出租类型", name = "rentType")
    private  String rentType;

    /**
     * 房屋面积
     */
    @ApiModelProperty(value = "房屋面积", name = "houseArea")
    private  Double houseArea;

    /**
     * 室
     */
    @ApiModelProperty(value = "室", name = "room")
    private  Integer room;

    /**
     * 朝向
     */
    @ApiModelProperty(value = "朝向", name = "forward")
    private  String forward;

    /**
     * 大楼名称
     */
    @ApiModelProperty(value = "大楼名称", name = "buildingName")
    private  String buildingName;

    /**
     * 房屋标题图
     */
    @ApiModelProperty(value = "房屋标题图", name = "housePhotoTitle")
    private  String housePhotoTitle;

    /**
     * 房屋标题
     */
    @ApiModelProperty(value = "房屋标题", name = "houseTitle")
    private  String houseTitle;

    /**
     * 房租出租类型中文名
     */
    @ApiModelProperty(value = "房租出租类型中文名", name = "rentTypeName")
    private  String rentTypeName;

    /**
     * 城市信息
     */
    @ApiModelProperty(value = "城市信息", name = "cityId")
    private Integer cityId;

    private String city;

    /**
     * 是否显示默认图片标志
     */
    @ApiModelProperty(value = "", name = "")
    private Integer isDefaultImage = 0;

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
        if(null!=cityId){
            this.city = CityEnum.getId(cityId);
        }
    }
}
