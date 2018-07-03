package com.toutiao.app.domain.subscribe;

import lombok.Data;

@Data
public class UserSubscribeDetailDo {
    /**
     * 专题类型
     */
    private Integer topicType;
    /**
     * 区域Id
     */
    private Integer districtId;
    /**
     * 区域
     */
    private String districtName;
    /**
     * 开始价格
     */
    private Integer beginPrice;
    /**
     * 结束价格
     */
    private Integer endPrice;
}
