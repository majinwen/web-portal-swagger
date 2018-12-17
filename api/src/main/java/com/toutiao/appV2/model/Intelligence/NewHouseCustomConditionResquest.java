package com.toutiao.appV2.model.Intelligence;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName NewHouseCustomConditionResquest
 * @Author jiangweilong
 * @Date 2018/12/17 10:26 AM
 * @Description:
 **/
@Data
public class NewHouseCustomConditionResquest {

    @ApiModelProperty(name = "districtId", value = "区域id")
    @JsonProperty("districtId")
    private String[] districtId = null;

    @ApiModelProperty(name = "pageNum", value = "当前页")
    @JsonProperty("pageNum")
    private Integer pageNum = 1;

    @ApiModelProperty(name = "pageSize", value = "每页大小")
    @JsonProperty("pageSize")
    private Integer pageSize = 10;

}
