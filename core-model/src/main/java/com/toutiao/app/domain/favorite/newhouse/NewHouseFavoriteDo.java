package com.toutiao.app.domain.favorite.newhouse;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class NewHouseFavoriteDo {

    /**
     * 新房收藏id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 新房id
     */
    private Integer buildingId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否删除(0-未删除，1-已删除)
     */
    private Short isDel;

    /**
     * 均价
     */
    private BigDecimal averagePrice;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 起始面积
     */
    private String houseMinArea;

    /**
     * 结束面积
     */
    private String houseMaxArea;

    /**
     * 楼盘名称
     */
    private String buildingName;

    /**
     * 标题图
     */
    private String buildingTitleImg;

    /**
     * 上下架(0-上架,1-下架)
     */
    private Short status;
}
