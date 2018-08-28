package com.toutiao.app.domain.message;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class HomeMessageDo {
    /**
     * 内容
     */
    private JSONObject content;

    /**
     * 未读条数
     */
    private long count;

    /**
     * 加粗字体
     */
    private String boldContent;

    /**
     * 内容类型(3-符合找房条件的房源上新, 4-关注小区房源上新, 5-关注房源价格变动, 6-订阅的主题有更新)
     */
    private Integer contentType;

    /**
     * 创建时间
     */
    private long createTime;
}
