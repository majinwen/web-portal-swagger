package com.toutiao.appV2.model.suggest;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * SearchEnginesResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T06:17:59.507Z")

@Data
public class SearchEnginesResponse {
    /**
     * 房源id
     */
    @ApiModelProperty("房源id")
    private Integer searchId;
    /**
     * 房源名称
     */
    @ApiModelProperty("房源名称")
    private String searchName;
    /**
     * 房源别名
     */
    @ApiModelProperty("房源别名")
    private String searchNickname;
    /**
     * 排序名称
     */
    @ApiModelProperty("排序名称")
    private String searchSort;
    /**
     * 房屋类型
     */
    @ApiModelProperty("房屋类型")
    private String searchType;
    /**
     * 房屋类型标志
     */
    @ApiModelProperty("房屋类型标志")
    private Integer searchTypeSings;
    /**
     * 区县名称
     */
    @ApiModelProperty("区县名称")
    private String district;
    /**
     * 区县id
     */
    @ApiModelProperty("区县id")
    private Integer districtId;
    /**
     * 商圈名称
     */
    @ApiModelProperty("商圈名称")
    private String area;
    /**
     * 商圈id
     */
    @ApiModelProperty("商圈id")
    private Integer areaId;
    /**
     * 城市id
     */
    @ApiModelProperty("城市id")
    private Integer cityId;

    /**
     * 城市id
     */
    @ApiModelProperty("是否区域跳转(0-房源，1-区县，2-商圈)")
    private Integer isArea;
}
