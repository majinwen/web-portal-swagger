package com.toutiao.app.api.chance.request.plot;

import com.toutiao.web.common.assertUtils.IntegerParaValidator;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PlotsRentRequest {
    /**
     * 小区id
     */
    @NotNull(message = "缺少小区Id")
    private Integer plotId;
    /**
     * 出租类型(整租:1/合租:2)
     */
    @IntegerParaValidator(value = "1,2",message = "出租类型错误")
    private Integer rentType;
    /**
     * 当前页
     */
    private Integer pageNum=1;
    private Integer pageSize=10;
}
