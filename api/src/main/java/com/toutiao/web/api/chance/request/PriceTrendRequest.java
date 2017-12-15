package com.toutiao.web.api.chance.request;

import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.assertUtils.Fourth;
import com.toutiao.web.common.assertUtils.Third;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by 18710 on 2017/12/14.
 */
@Data
public class PriceTrendRequest {
    @NotNull(groups={First.class,Fourth.class,Third.class},message = "缺少机会ID")
    private Integer buildId;

    @NotNull(groups={First.class,Fourth.class,Third.class},message = "缺少机会ID")
    private Short propertyType;

    private Short contrastDA;

    private Short month;

    private BigDecimal price;

}
