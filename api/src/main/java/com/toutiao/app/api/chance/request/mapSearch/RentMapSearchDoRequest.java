package com.toutiao.app.api.chance.request.mapSearch;

import com.toutiao.app.api.chance.request.BaseQueryRequest;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class RentMapSearchDoRequest extends BaseQueryRequest {

    @ApiParam(value = "组类型：区域district，商圈bizcircle，社区community")
    private String groupType;

    @ApiParam(value = "整租户型")
    private String elo = null;

    @ApiParam(value = "合租户型")
    private String jlo = null;

    @ApiParam(value = "整租:1/合租:2/未知:3")
    private String rentType = null;

    @ApiParam(value = "附近距离")
    private Integer distance;

    @ApiParam(value = "左上维度")
    private Double topLeftLat;

    @ApiParam(value = "左上经度")
    private Double topLeftLon;

    @ApiParam(value = "右下维度")
    private Double bottomRightLat;

    @ApiParam(value = "右下经度")
    private Double bottomRightLon;

}
