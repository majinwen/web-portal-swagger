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

    private Double preconcTotal;

    /**
     * 总价
     */
    private Double plotTotal;
    /**
     * 小区个数
     */
    private long plotCount;
    /**
     * 百分比
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
     * 区域的id
     */
    private Integer district_Id;
    /**
     * 教育配套标签标记
     */
    private  int flag=0;


}
