package com.toutiao.app.domain.favorite;

import lombok.Data;

/**
 * Created by wk on 2018/11/30.
 */
@Data
public class CancelFavoriteHouseDto {

    private Integer userId;

    private String id;

    private Integer type;

}
