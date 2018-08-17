package com.toutiao.web.dao.entity.officeweb;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class IntelligenceFhRes {
    /**
     * id
     */
    private Integer id;

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
    private Integer downPayment;

    /**
     * 月供
     */
    private Integer monthPayment;

    /**
     * 用户类型（1-自住刚需，2-自住改善，3-投资出租）
     */
    private Integer userType;

    /**
     * 总价
     */
    private Integer totalPrice;

    /**
     * 户型
     */
    private Integer layout;

    /**
     * 是否有小孩（0：无，1：0-3，2：5-10，3:10-13,4:14-18,5:18+）
     */
    private String hasChild;

    /**
     * 是否有老人(0-无，1-有)
     */
    private String hasOldman;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 用户画像
     */
    private Integer userPortrait;

    /**
     * 用户传递的区域id
     */
    private String districtId;

    /**
     * 小区到地铁站的距离
     */
    private String metroWithPlotdistance;


    private Integer collectStatus;

    private String layoutArray;

    private String districtArray;

}
