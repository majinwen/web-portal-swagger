package com.toutiao.app.domain.homepage;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-08-07
 * Time:   11:42
 * Theme:
 */
@Data
public class RecommendTopicDo {


    /**
     * 区域id
     */
    @ApiModelProperty("区域id")
    private String districtId;
    /**
     * 主题类型
     */
    @ApiModelProperty("主题类型")
    private String topicType;
    /**
     * 数量
     */
    @ApiModelProperty("数量")
    private Integer count;
    /**
     * 最高价格
     */
    @ApiModelProperty("最高价格")
    private Double highestPrice;
    /**
     * 最低价格
     */
    @ApiModelProperty("最低价格")
    private Double lowestPrice;
    /**
     * 是否订阅
     */
    @ApiModelProperty("是否订阅")
    private Integer isSubscribe;


}
