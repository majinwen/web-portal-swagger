package com.toutiao.web.domain.query;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
public class PlotRequest {
    /**
     * 小区id
     */
    @NotNull(message = "缺少小区Id")
    private Integer plotId;
}
