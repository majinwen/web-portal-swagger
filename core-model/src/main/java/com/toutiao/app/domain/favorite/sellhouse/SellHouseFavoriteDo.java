package com.toutiao.app.domain.favorite.sellhouse;

import com.toutiao.web.common.assertUtils.ChangeName;
import com.toutiao.web.common.constant.city.CityEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SellHouseFavoriteDo {

    /**
     * 二手房收藏id
     */
    @ApiModelProperty(value = "二手房收藏id", name = "id")
    private Integer id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", name = "userId")
    private Integer userId;

    /**
     * 二手房房源id
     */
    @ApiModelProperty(value = "二手房房源id", name = "houseId")
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
     * 是否下架
     */
    @ApiModelProperty(value = "是否下架", name = "status")
    private  Integer status;

    private  Integer  room;

    private  String buildingName;

    private  Double houseTotalPrices;

    private  String  housePhotoTitle;

    private  String  houseTitle;

    private  Double  buildArea;

    private  String forward;

    /**
     * 价格涨降标志
     */
    @ApiModelProperty(value = "价格涨降标志", name = "priceIncreaseDecline")
    @ChangeName("housePhotoTitleTags")
    private String priceIncreaseDecline;

    /**
     * 城市信息
     */
    @ApiModelProperty(value = "城市信息", name = "cityId")
    private Integer cityId;

    private String city;

    private Integer isDefaultImage = 0;


    public void setCityId(Integer cityId) {
        this.cityId = cityId;
        if(null!=cityId){
            this.city = CityEnum.getId(cityId);
        }
    }
}
