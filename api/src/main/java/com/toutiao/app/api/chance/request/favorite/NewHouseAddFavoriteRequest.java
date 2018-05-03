package com.toutiao.app.api.chance.request.favorite;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class NewHouseAddFavoriteRequest {

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Integer userId;

    /**
     * 新房id
     */
    @NotNull(message = "新房id不能为空")
    private Integer newHouseId;

    /**
     * 均价
     */
    @NotNull(message = "均价不能为空")
    private BigDecimal avgPrice;

    /**
     * 起始面积
     */
    @NotNull(message = "起始面积不能为空")
    private String beginArea;

    /**
     * 结束面积
     */
    @NotNull(message = "结束面积不能为空")
    private String endArea;

    /**
     * 楼盘名称
     */
    @NotNull(message = "楼盘名称不能为空")
    private String buildingName;

    /**
     * 标题图
     */
    @NotNull(message = "标题图不能为空")
    private String titleImage;

    /**
     * 上下架(0-上架,1-下架)
     */
    private Short status = 0;

    /**
     * 是否删除(0-未删除，1-已删除)
     */
    private Short isDel = 0;
}
