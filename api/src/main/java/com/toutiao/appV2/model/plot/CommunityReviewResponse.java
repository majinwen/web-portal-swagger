package com.toutiao.appV2.model.plot;

import com.toutiao.app.domain.plot.CommunityReviewDo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CummunityReviewResponse
 * @Author jiangweilong
 * @Date 2018/12/19 8:24 PM
 * @Description:
 **/

@Data
public class CommunityReviewResponse {

    @ApiModelProperty("社区综述")
    private CommunityReviewDo data;

}
