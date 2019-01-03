package com.toutiao.appV2.model.plot;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CummunityReviewRequest
 * @Author jiangweilong
 * @Date 2018/12/19 7:09 PM
 * @Description:
 **/

@Data
public class CummunityReviewRequest {

    @ApiModelProperty(value = "社区id",name = "communityId")
    private Integer communityId;
}
