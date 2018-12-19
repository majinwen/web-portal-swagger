package com.toutiao.app.domain.favorite.newhouse;

import com.toutiao.web.common.constant.city.CityEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class NewHouseFavoriteDo {

    /**
     * 新房收藏id
     */
    @ApiModelProperty(value = "新房收藏id", name = "id")
    private Integer id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", name = "userId")
    private Integer userId;

    /**
     * 新房id
     */
    @ApiModelProperty(value = "新房id", name = "buildingId")
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
     * 总价
     */
    @ApiModelProperty(value = "总价", name = "totalPrice")
    private BigDecimal totalPrice;

    /**
     * 起始面积
     */
    @ApiModelProperty(value = "起始面积", name = "houseMinArea")
    private String houseMinArea;

    /**
     * 结束面积
     */
    @ApiModelProperty(value = "结束面积", name = "houseMaxArea")
    private String houseMaxArea;

    /**
     * 楼盘名称
     */
    @ApiModelProperty(value = "楼盘名称", name = "buildingName")
    private String buildingName;

    /**
     * 标题图
     */
    @ApiModelProperty(value = "标题图", name = "buildingTitleImg")
    private String buildingTitleImg;

    /**
     * 上下架(0-上架,1-下架)
     */
    @ApiModelProperty(value = "上下架(0-上架,1-下架)", name = "status")
    private Short status;

    /**
     * 城市信息
     */
    @ApiModelProperty(value = "城市信息", name = "cityId")
    private Integer cityId;

    private String city;

    private String districtName;


    public void setCityId(Integer cityId) {
        this.cityId = cityId;
        if(null!=cityId){
            this.city = CityEnum.getId(cityId);
        }
    }

}
