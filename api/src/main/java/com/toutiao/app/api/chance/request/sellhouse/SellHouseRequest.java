package com.toutiao.app.api.chance.request.sellhouse;

import com.toutiao.app.api.chance.request.BaseQueryRequest;
import com.toutiao.web.common.assertUtils.First;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class SellHouseRequest extends BaseQueryRequest {

    /**
     * 推荐房源查询标志
     */
    @NotEmpty(groups = {First.class},message = "推荐房源查询标志为空")
    private String uid;
    /**
     * 附近1,3,5km
     *
     */
    private Integer distance ;

    /**
     * y坐标
     */
    private Double lat;
    /**
     * x坐标
     */
    private Double lon;

    /**
     * 排序字段
     */
//    @NotEmpty(groups = {First.class},message = "房源查询标志为空")
    private String sortFields;

    /**
     * 排序标志
     */
    private String sort;

    /**
     * 小区id
     */
    private Integer buildingId;

    /**
     * 是否降价房
     */
    private Integer isCutPrice;

    /**
     * 是否价格洼地
     */
    private Integer isLowPrice;

    /**
     * 是否逢出必抢
     */
    private Integer isMustRob;

}
