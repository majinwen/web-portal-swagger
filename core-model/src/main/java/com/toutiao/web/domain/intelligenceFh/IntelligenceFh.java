package com.toutiao.web.domain.intelligenceFh;

import lombok.Data;

@Data
public class IntelligenceFh {

    /**
     * 用户手机号
     */
    private String phone;
    /**
     * 智能找房结果
     */
    private Object fhResult;
    /**
     * 首付
     */
    private Double downPayMent;
    /**
     * 月供
     */
    private  Double monthPayMent;
    /**
     * 总价
     */
    private Double totalPrice;

    /**
     * 用户类型(1--自住 刚需,2--自住 改善,3--出租 投资)
     */
    private String userType;

    /**
     * 赛选出来的小区个数
     */
    private Integer plotCount;
    /**
     * 用户画像类型1,2,3,4,5,6,7
     */
    private Integer userPortrayalType;
    /**
     * 教育配套标签标记(有true,无false)
     */
    private Boolean schoolFlag;

    //医疗配套标签标记(有true,无false)

    private Boolean hospitalFlag;

    //用户填入的页面总价信息
    private Double preconcTotal;

    /**
     * 户型
     */
    private  Integer layOut;
    /**
     * 是否有老人(0-无，1-有)
     */
    private Integer hasOldMan;
    /**
     * 是否有小孩（0-（无、10+岁） ，1-（0-3岁），2-（4-10岁））
     */
    private Integer hasChild;
    /**
     * 学校类型 KG:幼儿园 L:小学
     */
    private String schoolType;
    /**
     * 用户与历史数据百分比
     */
    private String ratio;

    /**
     * 用户传递的区域id
     */
    private String districtId;

}