package com.toutiao.app.domain.mapSearch;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName EsfMapFindHouseCommunityDomain
 * @Author jiangweilong
 * @Date 2018/11/22 9:34 PM
 * @Description:
 **/

@Data
public class EsfMapSearchDomain {

    /**
     * 当前可视界面描述
     */
    @ApiModelProperty("当前可视界面描述")
    private String hit;

    /**
     * 返回数据
     */
    @ApiModelProperty("返回的数据")
    private List<EsfMapSearchDo> data;
}
