package com.toutiao.app.domain.sellhouse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SellHouseSubject {

    @ApiModelProperty(value = "专题说明", name = "text")
    private String text;

    @ApiModelProperty(value = "专题链接", name = "url")
    private String url;
}
