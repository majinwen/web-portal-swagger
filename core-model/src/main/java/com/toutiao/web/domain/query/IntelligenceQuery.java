package com.toutiao.web.domain.query;

import lombok.Data;

@Data
public class IntelligenceQuery {

    /**
     * 用户类型(1--自住 刚需,2--自住 改善,3--出租 投资)
     */
    private String userType;

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 首付(起始)
     */
    private Double beginDownPayment;

    /**
     * 月供(起始)
     */
    private Double beginMonthPayment;

    //用户填入的页面总价信息
    private Double preconcTotal;
    /**
     * 通过首付和月付计算出来的总价
     */
    private Double plotTotal;
    /**
     * 赛选出来的小区个数
     */
    private int plotCount;
    /**
     * 赛选出来的百分比
     */
    private String ratio;

    /**
     * 用户选择的户型
     */
    private Integer categoryId;

    /**
     * 用户画像类型1,2,3,4,5,6,7
     */
    private Integer userPortrayalType;

    /**
     * 用户赛选出来的商圈个数
     */
    private long docCount;

    /**
     * 通过年龄判定传递的学校类型
     */
    private String schoolTypeName;

    /**
     * 用户传递的区域id
     */
    private String districtId;
    /**
     * 教育配套标签标记
     */
    private int schoolFlag;

    //医疗配套标签标记

    private int hospitalFlag;

    //是否有小孩（0：无，1：0-3，2：5-10，3:10-13,4:14-18,5:18+）
    private Integer hasChild;

    //是否有老人(0-无，1-有)
    private Integer hasOldman;



}
