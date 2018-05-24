package com.toutiao.app.domain.favorite;

import lombok.Data;

@Data
public class IsFavoriteDo {


    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 二手房id和租房id
     */
    private String houseId;

}
