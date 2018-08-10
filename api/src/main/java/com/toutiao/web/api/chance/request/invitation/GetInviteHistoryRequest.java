package com.toutiao.web.api.chance.request.invitation;

import lombok.Data;

@Data
public class GetInviteHistoryRequest {
    /**
     * 邀请码
     */
    private Integer code;

    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 每页数量
     */
    private Integer pageSize = 10;
}
