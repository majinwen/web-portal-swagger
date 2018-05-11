package com.toutiao.app.api.chance.request.favorite;


import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class PlotsAddFavoriteResquest {
    /**
     * 小区id
     */
    @NotNull(message = "小区id不能为空")
    private Integer buildingId;
    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Integer userId;
    /**
     * 均价
     */
    @NotNull(message = "均价不能为空")
    private Double averagePrice;
    /**
     * 小区名称
     */
    @NotEmpty(message = "小区名称不能为空")
    private String buildingName;
    /**
     * 标题图
     */
    @NotEmpty(message = "标题图不能为空")
    private String buildingImages;
    /**
     * 是否下架(0-未下架, 1-下架)
     */
    private Short status = 0;
    /**
     * 是否删除(0-未删除，1-已删除)
     */
    private Short isDel = 0;
}
