package com.toutiao.appV2.model.rent;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.app.domain.rent.UserFavoriteRentDetailDo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by CuiShihao on 2018/12/11
 */
@Data
public class UserFavoriteRentListResponse {

    @JsonProperty("rentDetailsList")
    @Valid
    @ApiModelProperty("出租房源详情")
    private List<UserFavoriteRentDetailDo> userFavoriteRentDetails = null;

    @JsonProperty("totalCount")
    @ApiModelProperty("总数")
    private Integer totalCount = null;
}
