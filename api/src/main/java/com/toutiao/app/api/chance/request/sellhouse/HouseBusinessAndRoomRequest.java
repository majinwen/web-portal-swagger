package com.toutiao.app.api.chance.request.sellhouse;

import com.toutiao.app.api.chance.request.BaseQueryRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * 商圈+户型
 */
@Data
public class HouseBusinessAndRoomRequest extends BaseQueryRequest {
    /**
     * 商圈Id
     */
    @NotNull(message = "缺少商圈Id")
    private Integer areaId;

    /**
     * 商圈名称
     */
    @NotEmpty(message = "缺少商圈名称")
    private String areaName;

    /**
     * 详情页房源编号
     */
    //@NotNull(message = "缺少房源Id")
    private String houseId;
}
