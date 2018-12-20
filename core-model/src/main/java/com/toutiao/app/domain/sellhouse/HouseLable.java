package com.toutiao.app.domain.sellhouse;

import com.toutiao.web.common.constant.house.HouseLableEnum;
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

    @ApiModelProperty(value = "逛逛图标", name = "shakeIcon")
    private String shakeIcon;



    public HouseLable(String text, String icon, String url) {
        this.text = text;
        this.icon = icon;
        this.url = url;
    }

    public HouseLable(String text, String icon) {
        this.text = text;
        this.icon = icon;
    }

    public HouseLable(){
    }

    public HouseLable(HouseLableEnum houseLableEnum){
        this.setText(houseLableEnum.getKey());
        this.setIcon(houseLableEnum.getValue());
    }

}
