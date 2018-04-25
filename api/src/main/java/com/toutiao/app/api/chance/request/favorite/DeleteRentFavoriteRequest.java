package com.toutiao.app.api.chance.request.favorite;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class DeleteRentFavoriteRequest {
    /**
     * 租房id
     */
    @NotEmpty(message = "租房id不能为空")
    private String rentId;
    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Integer userId;
}
