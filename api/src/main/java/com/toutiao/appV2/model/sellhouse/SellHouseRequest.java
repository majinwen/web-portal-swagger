package com.toutiao.appV2.model.sellhouse;

import com.toutiao.appV2.model.BaseQueryRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@ApiModel(value = "SellHouseRequest", description = "SellHouseRequest")
public class SellHouseRequest extends BaseQueryRequest {

    @ApiModelProperty(value = "推荐房源查询标志", name = "uid")
    @NotEmpty(message = "推荐房源查询标志为空")
    private String uid;

    @ApiModelProperty(value = "附近1,3,5km", name = "distance")
    private Integer distance;

    @ApiModelProperty(value = "y坐标", name = "lat")
    private Double lat;

    @ApiModelProperty(value = "x坐标", name = "lon")
    private Double lon;

    @ApiModelProperty(value = "排序字段", name = "sortFields")
    private String sortFields;

    @ApiModelProperty(value = "排序标志", name = "sort")
    private String sort;

    @ApiModelProperty(value = "小区id", name = "buildingId")
    private Integer buildingId;

    @ApiModelProperty(value = "是否降价房", name = "isCutPrice")
    private Integer isCutPrice;

    @ApiModelProperty(value = "是否价格洼地", name = "isLowPrice")
    private Integer isLowPrice;

    @ApiModelProperty(value = "是否逢出必抢", name = "isMustRob")
    private Integer isMustRob;

}
