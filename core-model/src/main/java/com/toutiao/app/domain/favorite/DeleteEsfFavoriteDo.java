package com.toutiao.app.domain.favorite;

import lombok.Data;


@Data
public class DeleteEsfFavoriteDo {
    /**
     * 二手房id
     */
    private String houseId;
    /**
     * 用户id
     */
    private Integer userId;
}
