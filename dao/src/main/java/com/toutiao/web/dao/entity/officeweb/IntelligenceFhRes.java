package com.toutiao.web.dao.entity.officeweb;

import lombok.Data;

@Data
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
     *首付
     */
    private Integer downPayMent;
    /**
     *月供
     */
    private  Integer monthPayMent;
    /**
     *用户类型（1-自住刚需，2-自住改善，3-投资出租）
     */
    private Integer userType;
    /**
     *总价
     */
    private  Integer totalPrice ;
    /**
     *户型
     */
    private  Integer layOut;
    /**
     *是否有小孩（0：无，1：0-3，2：5-10，3:10-13,4:14-18,5:18+）
     */
    private  Integer hasChild;
    /**
     *是否有老人(0-无，1-有)
     */
    private Integer hasOldMan;


}