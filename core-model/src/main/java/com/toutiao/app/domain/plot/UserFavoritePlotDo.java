package com.toutiao.app.domain.plot;

import com.toutiao.web.common.assertUtils.ChangeName;
import com.toutiao.web.common.constant.city.CityEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserFavoritePlotDo {
    /**
     * 收藏小区id
     */
    @ApiModelProperty(value = "收藏小区id", name = "id")
    private Integer id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", name = "userId")
    private Integer userId;

    /**
     * 小区id
     */
    @ApiModelProperty(value = "小区id", name = "buildingId")
    private Integer buildingId;

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
     * 均价
     */
    @ApiModelProperty(value = "均价", name = "averagePrice")
    private BigDecimal averagePrice;

    /**
     * 小区名称
     */
    @ApiModelProperty(value = "小区名称", name = "buildingName")
    private String buildingName;

    /**
     * 标题图
     */
    @ApiModelProperty(value = "标题图", name = "buildingImages")
    private String buildingImages;

    /**
     * 上下架(0-上架, 1-下架)
     */
    @ApiModelProperty(value = "上下架(0-上架, 1-下架)", name = "status")
    private Short status;

    /**
     * 城市信息id
     */
    @ApiModelProperty(value = "城市信息id", name = "cityId")
    private Integer cityId;

    /**
     * 城市拼码
     */
    @ApiModelProperty(value = "城市拼码", name = "city")
    private String city;


    public void setCityId(Integer cityId) {
        this.cityId = cityId;
        if(null!=cityId){
            this.city = CityEnum.getId(cityId);
        }
    }
}
