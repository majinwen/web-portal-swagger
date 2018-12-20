package com.toutiao.app.domain.favorite;

import lombok.Data;

/**
 * Created by wk on 2018/11/28.
 */
@Data
public class FavoriteHouseListDoQuery {

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 当前页
     */
    private Integer pageNum ;
    /**
     * 每页大小
     */
    private Integer size ;

}
