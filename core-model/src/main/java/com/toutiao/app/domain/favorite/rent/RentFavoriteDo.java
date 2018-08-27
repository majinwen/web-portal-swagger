package com.toutiao.app.domain.favorite.rent;

import com.toutiao.web.common.constant.city.CityEnum;
import lombok.Data;

import java.util.Date;

@Data
public class RentFavoriteDo {

    /**
     * 租房收藏id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 租房id
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
     * 租金
     */
    private  Double rentPrice;

    /**
     * 上下架状态
     */
    private  Integer status;

    /**
     * 出租类型
     */
    private  String rentType;

    /**
     * 房屋面积
     */
    private  Double houseArea;

    /**
     * 室
     */
    private  Integer room;

    /**
     * 朝向
     */
    private  String forward;

    /**
     * 大楼名称
     */
    private  String buildingName;

    /**
     * 房屋标题图
     */
    private  String housePhotoTitle;

    /**
     * 房屋标题
     */
    private  String houseTitle;

    /**
     * 房租出租类型中文名
     */
    private  String rentTypeName;

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
