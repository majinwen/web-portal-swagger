package com.toutiao.app.domain.newhouse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NewHouseDynamicDo {

    @ApiModelProperty(value = "时间")
    private String time;

    @ApiModelProperty(value = "动态类型")
    private  Integer type;

    @ApiModelProperty(value = "标题")
    private  String title;

    @ApiModelProperty(value = "内容")
    private  String detail;

    @ApiModelProperty(value = "跳转地址")
     private  String isDel;

    @ApiModelProperty(value = "楼盘id")
    private  Integer newCode;

    @ApiModelProperty(value = "创建时间")
    private  String createTime;

    @ApiModelProperty(value = "动态跳转")
    private  String linkUrl;
}
