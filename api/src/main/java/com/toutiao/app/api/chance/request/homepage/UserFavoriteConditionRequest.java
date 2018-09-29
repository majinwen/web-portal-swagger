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
    private Double beginPrice = 0.0;
    /**
     * 结束价格
     */
    private Double endPrice;
    /**
     * 户型id
     */
    private String[] layoutId;
    /**
     * 城市
     */
    private String city;
    /**
     * 当前页
     */
    private Integer pageNum = 1;

    /**
     * 每页大小
     */
    private Integer pageSize = 10;
    /**
     * 是否有预设条件(0:无,1:有 默认0)
     */
    private Integer flag = 0;
}

