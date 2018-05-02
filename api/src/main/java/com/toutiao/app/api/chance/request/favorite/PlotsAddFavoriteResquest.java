package com.toutiao.app.api.chance.request.favorite;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class PlotsAddFavoriteResquest {
    /**
     * 小区id
     */
    @NotNull(message = "小区id不能为空")
    private Integer plotId;
    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Integer userId;

    /**
     * 创建时间
     */
    private String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    /**
     * 是否删除(0-未删除，1-已删除)
     */
    private Short isDel = 0;
}
