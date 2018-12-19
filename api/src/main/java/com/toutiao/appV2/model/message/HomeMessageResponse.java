package com.toutiao.appV2.model.message;


import com.toutiao.app.domain.message.HomeMessageDo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by CuiShihao on 2018/11/16
 */
@Data
public class HomeMessageResponse {

    @ApiModelProperty("首页消息集合")
    private List<HomeMessageDo> data;

    @ApiModelProperty("消息集合的size")
    private Integer totalNum;
}
