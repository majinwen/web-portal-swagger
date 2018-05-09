package com.toutiao.app.api.chance.request.favorite;

import com.toutiao.web.common.assertUtils.Second;
import lombok.Data;
import net.sf.jsqlparser.statement.select.First;

import javax.validation.constraints.NotNull;

@Data
public class IsFavoriteRequest {

    @NotNull(groups = {First.class,Second.class}, message = "缺少租房id")
    private String houseId;

    /**
     * 用户id
     */
    @NotNull(message = "缺少用户id")
    private Integer userId;




}
