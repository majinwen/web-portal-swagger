package com.toutiao.app.api.chance.request.suggest;

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
    private String property;
}
