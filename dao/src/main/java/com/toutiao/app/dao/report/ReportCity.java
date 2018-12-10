package com.toutiao.app.dao.report;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReportCity {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 二手房上月均价
     */
    @ApiModelProperty(value = "二手房上月均价")
    private String esfMonthPrice;

    /**
     * 二手房均价环比
     */
    @ApiModelProperty(value = "二手房均价环比")
    private String esfPriceHuanbi;

    /**
     * 二手房价格趋势，近6个月数据
     */
    @ApiModelProperty(value = "二手房价格趋势，近6个月数据")
    private String esfPriceFenbu;

    /**
     * 新房上月均价
     */
    @ApiModelProperty(value = "新房上月均价")
    private String newMonthPrice;

    /**
     * 新房均价环比
     */
    @ApiModelProperty(value = "新房均价环比")
    private String newPriceHuanbi;

    /**
     * 新房价格趋势，近6个月数据
     */
    @ApiModelProperty(value = "新房价格趋势，近6个月数据")
    private String newPriceRange;

    /**
     * 月度分析
     */
    @ApiModelProperty(value = "月度分析")
    private String monthDes;

    /**
     * 成交周期
     */
    @ApiModelProperty(value = "成交周期")
    private String chengjiaozhouqi;

    /**
     * 新房在售数量
     */
    @ApiModelProperty(value = "新房在售数量")
    private String newCount;

    /**
     * 二手房数量
     */
    @ApiModelProperty(value = "二手房数量")
    private String esfCount;

    /**
     * 租房数量
     */
    @ApiModelProperty(value = "租房数量")
    private String zfCount;

    /**
     * 二手房昨日上新
     */
    @ApiModelProperty(value = "二手房昨日上新")
    private String esfAdd;

    /**
     * 二手房被抢走
     */
    @ApiModelProperty(value = "二手房被抢走")
    private String esfQiangzou;

    /**
     * 二手房虚假下架
     */
    @ApiModelProperty(value = "二手房虚假下架")
    private String esfXujiaxiajia;

    /**
     * 二手房处理投诉
     */
    @ApiModelProperty(value = "二手房处理投诉")
    private String esfChulitousu;

    /**
     * 二手房赔付金额
     */
    @ApiModelProperty(value = "二手房赔付金额")
    private String esfPeifujine;

    /**
     * 二手房价格分布
     */
    @ApiModelProperty(value = "二手房价格分布")
    private String esfPriceRange;

    /**
     * 二手房特色房源：捡漏房
     */
    @ApiModelProperty(value = "二手房特色房源：捡漏房")
    private String esfTeseJianlou;

    /**
     * 二手房特色房源：降价房
     */
    @ApiModelProperty(value = "二手房特色房源：降价房")
    private String esfTeseJiangjia;

    /**
     * 二手房特色房源：抢手房
     */
    @ApiModelProperty(value = "二手房特色房源：抢手房")
    private String esfTeseQiangshou;

    /**
     * 租房整租数量
     */
    @ApiModelProperty(value = "租房整租数量")
    private String zfZhengzuCount;

    /**
     * 租房合租数量
     */
    @ApiModelProperty(value = "租房合租数量")
    private String zfHezuCount;

    /**
     * 租房整租上新
     */
    @ApiModelProperty(value = "租房整租上新")
    private String zfZhengzuShangxin;

    /**
     * 租房整租已租
     */
    @ApiModelProperty(value = "租房整租已租")
    private String zfZhengzuYizu;

    /**
     * 租房合租上新
     */
    @ApiModelProperty(value = "租房合租上新")
    private String zfHezuShangxin;

    /**
     * 租房合租已租
     */
    @ApiModelProperty(value = "租房合租已租")
    private String zfHezuYizu;

    /**
     * 租房免租金数量
     */
    @ApiModelProperty(value = "租房免租金数量")
    private String zfMianzujinCount;

    /**
     * 租房免租金上新
     */
    @ApiModelProperty(value = "租房免租金上新")
    private String zfMianzujinShangxin;

    /**
     * 租房免租金已租
     */
    @ApiModelProperty(value = "租房免租金已租")
    private String zfMianzujinYizu;

    /**
     * 租房品牌公寓数量
     */
    @ApiModelProperty(value = "租房品牌公寓数量")
    private String zfPinpaiCount;

    /**
     * 租房品牌公寓上新
     */
    @ApiModelProperty(value = "租房品牌公寓上新")
    private String zfPinpaiShangxin;

    /**
     * 租房品牌公寓已租
     */
    @ApiModelProperty(value = "租房品牌公寓已租")
    private String zfPinpaiYizu;

    /**
     * 租房价格分布
     */
    @ApiModelProperty(value = "租房价格分布")
    private String zfPriceRange;

    /**
     * 新房昨日均价
     */
    @ApiModelProperty(value = "新房昨日均价")
    private String newYestodayPrice;

    /**
     * 新房本月开盘
     */
    @ApiModelProperty(value = "新房本月开盘")
    private String newBenyuekaipan;

    /**
     * 城市id
     */
    @ApiModelProperty(value = "城市id")
    private Integer cityId;

    /**
     * 城市名称
     */
    @ApiModelProperty(value = "城市名称")
    private String cityName;

    /**
     * 新房指南人气榜
     */
    @ApiModelProperty(value = "新房指南人气榜")
    private String newGuidePopular;

    /**
     * 新房指南销售榜
     */
    @ApiModelProperty(value = "新房指南销售榜")
    private String newGuideSales;

    /**
     * 新房指南关注榜
     */
    @ApiModelProperty(value = "新房指南关注榜")
    private String newGuideAttention;

    /**
     * 新房指南热门楼盘榜
     */
    @ApiModelProperty(value = "新房指南热门楼盘榜")
    private String newGuideHot;

    /**
     * 优惠楼盘
     */
    @ApiModelProperty(value = "优惠楼盘")
    private String newPreferential;

    /**
     * 二手房热门小区
     */
    @ApiModelProperty(value = "二手房热门小区")
    private String esfPlotHot;

    /**
     * 热门商圈
     */
    @ApiModelProperty(value = "热门商圈")
    private String areaHot;
}
