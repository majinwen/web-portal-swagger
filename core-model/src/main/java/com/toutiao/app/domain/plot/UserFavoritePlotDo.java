package com.toutiao.app.domain.plot;

import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserFavoritePlotDo {
    /**
     * 收藏小区id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 小区id
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
     * 小区名称
     */
    private String buildingName;

    /**
     * 标题图
     */
    private String buildingImages;

    /**
     * 上下架(0-上架, 1-下架)
     */
    private Short status;
}
