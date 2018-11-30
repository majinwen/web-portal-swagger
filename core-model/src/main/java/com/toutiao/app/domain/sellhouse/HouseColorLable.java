package com.toutiao.app.domain.sellhouse;

import com.toutiao.web.common.constant.house.HouseLableEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class HouseColorLable {

    @ApiModelProperty(value = "背景色", name = "backColor")
    private String backColor;

    @ApiModelProperty(value = "字体色", name = "fontColor")
    private String fontColor;

    @ApiModelProperty(value = "文本", name = "text")
    private String text;

    @ApiModelProperty(value = "链接", name = "url")
    private String url;
}
