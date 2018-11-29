package com.toutiao.app.domain.favorite;

import lombok.Data;

import java.util.List;

/**
 * Created by wk on 2018/11/28.
 */
@Data
public class FavoriteHouseDomain {

    private List<FavoriteHouseVo> list;

    private Integer totalCount;

}
