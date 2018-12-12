package com.toutiao.app.domain.rent;

import lombok.Data;

import java.util.List;

/**
 * Created by CuiShihao on 2018/12/11
 */
@Data
public class UserFavoriteRentListDomain {

    /**
     * 出租房源详情
     */
    private List<UserFavoriteRentDetailDo> favoriteRentDetails = null;

    /**
     * 总数
     */
    private Integer totalCount = null;
}
