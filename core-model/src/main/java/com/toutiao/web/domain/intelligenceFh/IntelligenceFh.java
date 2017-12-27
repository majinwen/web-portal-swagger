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
    private Integer downPayMent;
    /**
     * 月供
     */
    private  Integer monthPayMent;
    /**
     * 总价
     */
    private Integer totalPrice;
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

}