package com.toutiao.web.domain.payment;

import lombok.Data;

/**
 * 支付备注实体类
 *
 */
@Data
public class CommentDo {

    /**
     * 楼盘id
     */
    private Integer buildingId;

    /**
     * 楼盘名称
     */
    private String buildingName;

    /**
     * 楼盘标题图
     */
    private String buildingTitleImg;

}
