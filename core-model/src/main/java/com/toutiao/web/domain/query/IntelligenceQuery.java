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
    private String downPayMent;

    /**
     * 月供(起始)
     */
    private String monthPayMent;

    //用户填入的页面总价信息
    private String preconcTotal;
    /**
     *
     */
    private String totalPrice;
    /**
     * 赛选出来的小区个数
     */
    private int plotCount;
    /**
     * 赛选出来的百分比
     */
    private Double ratio;

    /**
     * 用户选择的户型
     */
    private Integer layOut;

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
    private Integer  schoolFlag=0;

    /**
     * 医疗配套标签标记
     */
    private Integer  hospitalFlag=0;

    /**
     * 是否有小孩（0：无，1：0-3，2：5-10，3:10-13,4:14-18,5:18+）
     */
    private Integer hasChild;

    /**
     * 是否有老人(0-无，1-有)
     */
    private Integer hasOldman;

    /**
     * 最小值
     */
    private Double minTotalPrice;

    /**
     * 最大值
     */
    private Double maxTotalPrice;

    /**
     * 学校
     */
    private String school;

    /**
     * 医院
     */
    private String hospital;

    /**
     * 创建时间
     */
    private Integer createTime;

    /**
     * 用户画像
     */
    private Integer userPortrait;







}
