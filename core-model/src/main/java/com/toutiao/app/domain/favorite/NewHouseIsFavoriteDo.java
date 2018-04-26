package com.toutiao.app.domain.favorite;

import lombok.Data;

@Data
public class NewHouseIsFavoriteDo {
    /**
     * 新房id
     */
    private Integer newHouseId;
    /**
     * 用户id
     */
    private Integer userId;
}
