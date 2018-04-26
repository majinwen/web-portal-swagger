package com.toutiao.app.domain.favorite;

import lombok.Data;

@Data
public class DeleteRentFavoriteDo {
    /**
     * 租房id
     */
    private String rentId;
    /**
     * 用户id
     */
    private Integer userId;
}
