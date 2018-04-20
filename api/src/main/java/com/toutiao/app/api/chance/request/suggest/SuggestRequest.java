package com.toutiao.app.api.chance.request.suggest;

import com.toutiao.web.common.assertUtils.HouseTypeValidator;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class SuggestRequest {
    /**
     * 关键字
     */
    @NotEmpty(message = "缺少关键字")
    private String keyword;

    /**
     * 房源类型
     */
    @HouseTypeValidator(value = "plot,sellhouse,newhouse",message = "房源路径类型错误")
    private String property;
}
