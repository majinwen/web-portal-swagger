package com.toutiao.web.api.chance.request.message;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MessageIsReadRequest {

    /**
     * 内容类型(3-符合找房条件的房源上新, 4-关注小区房源上新, 5-关注房源价格变动, 6-订阅的主题有更新)
     */
    @NotNull(message = "内容类型不能为空")
    private Integer contentType;

    /**
     * 时间
     */
    @NotNull(message = "时间不能为空")
    private Long time;
}
