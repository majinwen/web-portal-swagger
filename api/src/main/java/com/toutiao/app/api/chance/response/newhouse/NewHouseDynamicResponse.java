package com.toutiao.app.api.chance.response.newhouse;

import lombok.Data;

@Data
public class NewHouseDynamicResponse {

    /**
     *时间
     */
    private String time;

    /**
     *  动态类型
     */
    private  Integer type;

    /**
     * 标题
     */
    private  String title;

    /**
     * 内容
     */
    private  String detail;

    /**
     *跳转地址
     */
    private  String isDel;

    /**
     * 楼盘id
     */
    private  Integer newCode;

    /**
     * 创建时间
     */
    private  String createTime;


}
