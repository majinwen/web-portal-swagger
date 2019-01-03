package com.toutiao.appV2.model.version;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by CuiShihao on 2018/12/27
 */
@Data
public class VersionResponse {

    @ApiModelProperty(value = "是否有新版本（0-没有新版本  其他表示最新的版本）", name = "isNew")
    private Integer isNew;

    @ApiModelProperty(value = "版本对应的下载地址", name = "url")
    private String url;

    @ApiModelProperty(value = "版本号", name = "version")
    private Integer version;
}
