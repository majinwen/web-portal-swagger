package com.toutiao.app.domain.newhouse;

import lombok.Data;

@Data
public class NewHouseDynamicDo {

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
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 每页数量
     */
    private Integer pageSize=10;

    /**
     * 创建时间
     */
    private  String createTime;

    /**
     * 动态跳转
     */
    private  String linkUrl;
}
