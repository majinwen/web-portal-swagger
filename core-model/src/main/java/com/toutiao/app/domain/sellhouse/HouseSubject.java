package com.toutiao.app.domain.sellhouse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class HouseSubject {

    @ApiModelProperty(value = "名称", name = "text")
    private String text;

    @ApiModelProperty(value = "链接", name = "url")
    private String url;
}
