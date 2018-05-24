package com.toutiao.app.api.chance.request.favorite;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NewHouseIsFavoriteResquest {
    /**
     * 新房id
     */
    @NotNull(message = "新房id不能为空")
    private Integer buildingId;
    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Integer userId;
}
