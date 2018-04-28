package com.toutiao.app.api.chance.request.rent;

import com.toutiao.app.api.chance.request.BaseQueryRequest;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class NearHouseListRequest extends BaseQueryRequest {

    /**
     * 附近距离
     */
    private Integer distance = 5;
    /**
     * 整租:1/合租:2/未知:3
     */
    private String rentType;
    /**
     * 来源id
     */
    private String source;

    /**
     * 维度
     */
    @NotNull(message = "维度不能为空")
    private Double lat;
    /**
     * 经度
     */
    @NotNull(message = "经度不能为空")
    private Double lon;


}
