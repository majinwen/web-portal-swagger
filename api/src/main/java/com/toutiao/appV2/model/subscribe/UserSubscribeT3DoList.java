package com.toutiao.appV2.model.subscribe;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserSubscribeT3DoList {

    @ApiModelProperty(value = "收藏信息列表", name = "totalCount")
    List<UserSubscribeT3Do> list;

    /**
     * 总数
     */
    @ApiModelProperty(value = "总数", name = "totalCount")
    private long totalCount;
}
