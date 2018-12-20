package com.toutiao.app.domain.plot;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CommunityReviewDo
 * @Author jiangweilong
 * @Date 2018/12/19 7:02 PM
 * @Description:
 **/

@Data
public class CommunityReviewDo {

    @ApiModelProperty(name = "summarize", value = "综述")
    private String summarize;
    @ApiModelProperty(name = "merits", value = "优点")
    private String merits;
    @ApiModelProperty(name = "demerits", value = "缺点")
    private String demerits;
    @ApiModelProperty(name = "crowd", value = "人群")
    private String crowd;
    @ApiModelProperty(name = "parking", value = "车位")
    private String parking;
    @ApiModelProperty(name = "property", value = "物业")
    private String property;
    @ApiModelProperty(name = "traffic", value = "交通")
    private String traffic;
    @ApiModelProperty(name = "coordinate", value = "配套")
    private String coordinate;

}
