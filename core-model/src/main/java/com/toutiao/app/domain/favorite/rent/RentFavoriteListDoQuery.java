package com.toutiao.app.domain.favorite.rent;

import lombok.Data;

@Data
public class RentFavoriteListDoQuery {
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

    private Integer from;

}
