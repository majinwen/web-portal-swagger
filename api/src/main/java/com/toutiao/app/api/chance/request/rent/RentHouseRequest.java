package com.toutiao.app.api.chance.request.rent;

import com.toutiao.app.api.chance.request.BaseQueryRequest;
import com.toutiao.web.common.assertUtils.First;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;


@Data
public class RentHouseRequest extends BaseQueryRequest {

    /**
     * 附近距离
     */
    private Integer distance;
    /**
     * 整租:1/合租:2/未知:3
     */
    private String rentType;
    /**
     * 来源id
     */
    private String source;
    /**
     * 维度
     */

    private Double lat;
    /**
     * 经度
     */
    private Double lon;

    /**
     * 租房推优查询uid
     */
    @NotEmpty(groups = {First.class},message = "缺少查询uid")
    private String uid;

    /**
     * 户型
     */
    private String elo;//整租户型

    private String jlo;//合租户型

}
