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
    private Integer buildId;

    /**
     * 楼盘名称
     */
    private String buildName;

}
