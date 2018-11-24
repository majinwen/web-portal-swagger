package com.toutiao.app.api.chance.request.rent;

import com.toutiao.app.api.chance.request.BaseQueryRequest;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.assertUtils.Second;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;


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
    @NotEmpty(groups = {Second.class},message = "缺少维度")
    private Double lat;
    /**
     * 经度
     */
    @NotEmpty(groups = {Second.class},message = "缺少经度")
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

    /**
     * 时间
     */
    @NotEmpty(groups = {Second.class},message = "缺少时间")
    private Date time;

    /**
     * 交通类型(0:不行,1:骑车,2:公交,3:驾车)
     */
    @NotEmpty(groups = {Second.class},message = "缺少交通类型")
    private String trafficType;


}
