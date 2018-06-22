package com.toutiao.web.domain.advertisement;

import lombok.Data;

@Data
public class AggAdLandingDo {

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
     * 小户型（1,2局）
     */
    private String ls;

    /**
     * 豪宅（大于1000w）
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
     *  页面上已经存在的数据量
     */
    private Integer existNum;

    /**
     * 区县
     */
    private Integer district;
    /**
     * 区县（居室）
     */
    private String room;

    private Integer count;
    /**
     * 房源面积
     */
    private String houseArea;
    /**
     * 房源面积标志  1.等于，2大于，3小于，4之间
     */
    private Integer haSign;
    /**
     * 整租
     */
    private Integer rentType;
    /**
     * 品质公寓(1:true)
     */
    private String qualityRent;

    /**
     * 天
     */
    private Integer days;


    private Integer priceID;

    /**
     * 商圈
     */
    private String areaId;

}
