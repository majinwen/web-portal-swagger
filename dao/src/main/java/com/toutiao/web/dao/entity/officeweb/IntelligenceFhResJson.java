package com.toutiao.web.dao.entity.officeweb;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class IntelligenceFhResJson {
    /**
     * id
     */
    @JSONField(name = "userId")
    private Integer id;

    /**
     * 用户手机号
     */
    @JSONField(name = "userPhone")
    private String phone;

    /**
     * 智能找房结果
     */
    @JSONField(name = "")
    private Object fhResult;

    /**
     * 首付
     */
    @JSONField(name = "downPayMent")
    private Integer downPayment;

    /**
     * 月供
     */
    @JSONField(name = "monthPayMent")
    private Integer monthPayment;

    /**
     * 用户类型（1-自住刚需，2-自住改善，3-投资出租）
     */
    @JSONField(name = "userType")
    private Integer userType;

    /**
     * 总价
     */
    @JSONField(name = "preconcTotal")
    private Integer totalPrice;

    /**
     * 户型
     */
    @JSONField(name = "layOut")
    private Integer layout;

    /**
     * 是否有小孩（0：无，1：0-3，2：5-10，3:10-13,4:14-18,5:18+）
     */
    @JSONField(name = "hasChild")
    private Integer hasChild;

    /**
     * 是否有老人(0-无，1-有)
     */
    @JSONField(name = "hasOldman")
    private Integer hasOldman;

    /**
     * 创建时间
     */
    @JSONField(name = "createTime")
    private String createTime;

    /**
     * 用户画像
     */
    @JSONField(name = "userPortrayalType")
    private Integer userPortrait;

    /**
     * 用户传递的区域id
     */
    @JSONField(name = "districtId")
    private String districtId;


}
