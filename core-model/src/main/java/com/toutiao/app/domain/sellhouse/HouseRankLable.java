package com.toutiao.app.domain.sellhouse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class HouseRankLable {

    @ApiModelProperty(value = "背景色", name = "backColor")
    private String backColor;

    @ApiModelProperty(value = "字体色", name = "fontColor")
    private String fontColor;

    @ApiModelProperty(value = "文本", name = "text")
    private String text;

    @ApiModelProperty(value = "描述", name = "desc")
    private String desc;

    @ApiModelProperty(value = "链接", name = "url")
    private String url;

    public HouseRankLable(String backColor, String fontColor, String text, String desc, String url) {
        this.backColor = backColor;
        this.fontColor = fontColor;
        this.text = text;
        this.desc = desc;
        this.url = url;
    }

    public HouseRankLable() {
    }
}
