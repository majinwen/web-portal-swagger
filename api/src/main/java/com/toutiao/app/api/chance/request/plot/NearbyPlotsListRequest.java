package com.toutiao.app.api.chance.request.plot;

import com.toutiao.app.api.chance.request.BaseQueryRequest;
import com.toutiao.web.common.assertUtils.First;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class NearbyPlotsListRequest extends BaseQueryRequest{

    /**
     * 维度 附近找房
     */
    @NotNull(groups = {First.class},message = "缺少坐标维度")
    private Double lat;
    /**
     * 经度 附近找房
     */
    @NotNull(groups = {First.class},message = "缺少坐标经度")
    private Double lon;
    /**
     * 附近列表默认距离
     */
    @NotEmpty(groups = {First.class},message = "缺少默认距离")
    private String distance;


}
