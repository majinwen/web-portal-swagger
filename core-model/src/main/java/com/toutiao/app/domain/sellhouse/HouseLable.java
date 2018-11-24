package com.toutiao.app.domain.sellhouse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class HouseLable {

    @ApiModelProperty(value = "图标说明", name = "text")
    private String text;

    @ApiModelProperty(value = "图标", name = "icon")
    private String icon;

    @ApiModelProperty(value = "链接", name = "url")
    private String url;
}
