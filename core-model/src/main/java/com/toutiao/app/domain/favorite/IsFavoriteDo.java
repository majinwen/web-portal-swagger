package com.toutiao.app.domain.favorite;

import lombok.Data;

@Data
public class IsFavoriteDo {

    /**
     * 租房id
     */
    private String rentId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 二手房id
     */
    private String houseId;

}
