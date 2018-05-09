package com.toutiao.app.api.chance.request.favorite;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class DeleteEsfFavoriteResquest {
    /**
     * 二手房id
     */
    @NotEmpty(message = " 二手房id无信息")
    private String houseId;
    /**
     * 用户id
     */
    @NotNull(message = "用户id无信息")
    private Integer userId;
}
