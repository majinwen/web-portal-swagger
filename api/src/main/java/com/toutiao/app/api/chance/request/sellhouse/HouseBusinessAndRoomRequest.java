package com.toutiao.app.api.chance.request.sellhouse;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * 商圈+户型
 */
@Data
public class HouseBusinessAndRoomRequest {
    /**
     * 商圈
     */
    @NotEmpty(message = "缺少商圈")
    private String houseBusinessName;

    /**
     * 商圈Id
     */
    @NotNull(message = "缺少商圈Id")
    private Integer houseBusinessId;

    /**
     * 户型
     */
    private Integer room;

    /**
     * 详情页房源编号
     */
    //@NotNull(message = "缺少房源Id")
    private Integer houseId;

    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 每页数量
     */
    private Integer pageSize = 10;
}
