package com.toutiao.web.api.chance.request.advertisement;


import lombok.Data;

@Data
public class AggAdLandingRequest {

    /**
     * 房源标签（满5，满2）
     */
    private String tag;

    /**
     * 近地铁
     */
    private String ns;

    /**
     * 房源总价 起始价 (0-200w)
     */
    private String bp;

    /**
     * 房源总价 结束价 (0-200w)
     */
    private String ep;

    /**
     * 小户型（90㎡内）
     */
    private String ls;

    /**
     * 豪宅 （200㎡以上）
     */
    private String lh;

    /**
     * 页码
     */
    private Integer pn = 1;

    /**
     * 每页数量
     */
    private Integer ps=10;

    /**
     * 用户浏览的起始位置
     */
    private Long startBit;

    /**
     * 查询位置
     */
    private Long queryBit;

    /**
     * 是否补充
     */
    private Integer sign = 0;

    /**
     * 推荐房源
     */
    private Integer tj;

    /**
     * 整租
     */
    private Integer rentType;

    /**
     * 区县
     */
    private Integer district;

    /**
     * 区县（居室）
     */
    private String room;
    /**
     * 品质公寓(1:true)
     */
    private String qualityRent;

    /**
     * 天
     */
    private Integer days;
    /**
     * 二手房涨价/降价标志
     */
    private Integer priceID;
}
