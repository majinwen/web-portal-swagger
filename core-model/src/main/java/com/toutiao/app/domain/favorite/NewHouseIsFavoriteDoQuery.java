package com.toutiao.app.domain.favorite;

import lombok.Data;

@Data
public class NewHouseIsFavoriteDoQuery {
    /**
     * 新房id
     */
    private Integer buildingId;
    /**
     * 用户id
     */
    private Integer userId;
}
