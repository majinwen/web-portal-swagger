package com.toutiao.app.domain.sellhouse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class HouseSubjectListResponse {

    @ApiModelProperty(value = "", name = "")
    private List<HouseSubject> houseSubjectList;

    @ApiModelProperty(value = "", name = "")
    private long totalCount;
}
