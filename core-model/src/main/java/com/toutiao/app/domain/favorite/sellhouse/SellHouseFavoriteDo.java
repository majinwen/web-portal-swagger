package com.toutiao.app.domain.favorite.sellhouse;

import com.toutiao.web.common.assertUtils.ChangeName;
import com.toutiao.web.common.constant.city.CityEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SellHouseFavoriteDo {

    /**
     * 二手房收藏id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 二手房房源id
     */
    private String houseId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否删除(0-未删除，1-已删除)
     */
    private Short isDel;

    /**
     * 是否下架
     */
    private  Integer status;

    private  Integer  room;

    private  String buildingName;

    private  Double houseTotalPrice;

    private  String  housePhotoTitle;

    private  String  houseTitle;

    private  Double  houseArea;

    private  String forward;

    /**
     * 价格涨降标志
     */
    @ChangeName("housePhotoTitleTags")
    private String priceIncreaseDecline;

    /**
     * 城市信息
     */
    private Integer cityId;

    private String city;


    public void setCityId(Integer cityId) {
        this.cityId = cityId;
        if(null!=cityId){
            this.city = CityEnum.getId(cityId);
        }
    }
}
