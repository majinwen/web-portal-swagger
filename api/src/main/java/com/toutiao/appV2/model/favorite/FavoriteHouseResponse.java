package com.toutiao.appV2.model.favorite;

import com.toutiao.app.domain.favorite.FavoriteHouseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by wk on 2018/11/28.
 */
@Data
public class FavoriteHouseResponse {

    @ApiModelProperty(value = "收藏房源", name = "list")
    private List<FavoriteHouseVo> list;

    @ApiModelProperty(value = "总条数", name = "totalCount")
    private Integer totalCount;

}
