package com.toutiao.app.api.chance.request.rent;

import com.toutiao.web.common.assertUtils.First;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class RecommendRentRequest {

    /**
     * 关键字
     */
    private String keyword;
    /**
     * 区域id
     */
    private Integer districtId;
    /**
     * 商圈id
     */
    private Integer areaId;
    /**
     * 地铁线id
     */
    private Integer subwayLineId;
    /**
     * 地铁站id
     */
    private Integer subwayStationId;
    /**
     * 附近距离
     */
    private Integer distance;
    /**
     * 起始租金
     */
    private Integer beginPrice;
    /**
     * 结束租金
     */
    private Integer endPrice;
    /**
     * 整租:1/合租:2/未知:3
     */
    private String rentType;
    /**
     * 房源面积
     */
    private String rentHouseArea;
    /**
     * 几居
     */
    private String room;
    /**
     * 朝向id
     */
    private String forward;
    /**
     * 来源id
     */
    private String source;
    /**
     * 标签
     */
    private String tags;
    /**
     * 维度
     */
    @NotNull
    private Double lat;
    /**
     * 经度
     */
    @NotNull
    private Double lon;
    /**
     * 当前页
     */
    private Integer pageNum = 1;
    /**
     * 城市id
     */
    private Integer cityId = 12;
    /**
     * 导入:3/录入:1
     */
    private Integer rentHouseType = 1;
    /**
     * 过滤标志
     */
    @NotEmpty(groups = {First.class},message = "缺少查询id")
    private String uid;


}
