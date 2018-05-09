package com.toutiao.app.api.chance.request.favorite;

import com.alibaba.fastjson.annotation.JSONField;
import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

import javax.validation.constraints.NotNull;

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
    @NotNull(message = "小区名称不能为空")
    private String buildingName;
    /**
     * 标题图
     */
    @NotNull(message = "标题图不能为空")
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
