package com.toutiao.app.domain.plot;

import com.toutiao.web.common.assertUtils.ChangeName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class PlotTop50Do {

    /**
     * 均价
     */
    //@ChangeName("averagePrice")
    private Double avgPrice;

    /**
     * 小区编号
     */
    //@ChangeName("buildingId")
    private Integer id;

    /**
     * 小区照片
     */
    //@ChangeName("buildingImages")
    private String[] photo;

    /**
     * 标题图
     */
    private String titlePhoto;


    /**
     * 近地铁描述
     */
    private String trafficInformation;


    /**
     * 在商圈种排名
     */
    private Integer rankAvgInBizcircle;


    /**
     * 待售二手房数量
     */
    private Integer houseCount;

    /**
     * 最低价
     */
    private Double lowestPrice;


    /**
     * 商圈
     */
    //@ChangeName("areaName")
    private String tradingArea;

    /**
     * 区域
     */
    // @ChangeName("districtName")
    private String area;

    /**
     * 大楼名称
     */
    private String rc;

//    /**
//     * 平均成交周期
//     */
//    private Integer avgDeal;

    /**
     * 推荐理由
     */
    private CommunityReviewDo recommendReason;

    /**
     * 小区推荐标签
     */
    private List<String> recommendBuildTagsName;

    /**
     * 小区自带标签
     */
    private List<String> label;

    /**
     * 小区行情
     */
    private PlotMarketDomain plotMarketDomain;
    /**
     * 标签名称
     */
    private String tagsName;
}