package com.toutiao.app.api.chance.request.favorite;

import lombok.Data;
import net.sf.jsqlparser.statement.select.First;

import javax.validation.constraints.NotNull;

@Data
public class IsFavoriteRequest {

    @NotNull(groups = {First.class}, message = "缺少租房id")
    private String rentId;

    /**
     * 用户id
     */
    private Integer userId;

}
