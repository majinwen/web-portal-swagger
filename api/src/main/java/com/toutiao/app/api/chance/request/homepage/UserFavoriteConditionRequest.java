package com.toutiao.app.api.chance.request.homepage;

import com.toutiao.web.common.assertUtils.First;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserFavoriteConditionRequest {
    /**
     * 用户id
     */
    @NotNull(groups = {First.class},message = "用户id不能为空")
    private Integer userId;
    /**
     * 区域id
     */
    private String[] districtId;
    /**
     * 起始价格
     */
    private Double beginPrice;
    /**
     * 结束价格
     */
    private Double endPrice;
    /**
     * 户型id
     */
    private String[] layoutId;
}

