package com.toutiao.app.api.chance.request.favorite;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NewHouseFavoriteListRequest {
    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Integer userId;
    /**
     * 当前页
     */
    private Integer pageNum = 1;
    /**
     * 每页大小
     */
    private Integer size =10;
}
