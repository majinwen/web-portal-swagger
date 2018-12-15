package com.toutiao.appV2.model.Intelligence;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.app.domain.newhouse.NewHouseCustomConditionDo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName NewHouseCustomConditionResponse
 * @Author jiangweilong
 * @Date 2018/12/14 6:18 PM
 * @Description:
 **/

@Data
public class NewHouseCustomConditionResponse {

    /**
     * 目标市场新房推荐
     */
    @ApiModelProperty(name = "data", value = "目标市场新房推荐")
    @JsonProperty("data")
    private List<NewHouseCustomConditionDo> data;

}
