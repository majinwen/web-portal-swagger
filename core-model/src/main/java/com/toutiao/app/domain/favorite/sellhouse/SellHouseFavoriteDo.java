package com.toutiao.app.domain.favorite.sellhouse;

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

    private  Double totalPrice;

    private  String  housePhotoTitle;

    private  String  houseTitle;

    private  Double  houseArea;

    private  String forward;
}
