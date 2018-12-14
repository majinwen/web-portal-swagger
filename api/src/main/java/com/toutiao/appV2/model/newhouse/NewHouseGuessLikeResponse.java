package com.toutiao.appV2.model.newhouse;

import com.toutiao.app.domain.newhouse.NewHouseListDo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by wk on 2018/12/13.
 */
@Data
public class NewHouseGuessLikeResponse {

    @ApiModelProperty(value = "搜索新房列表",name = "data")
    private List<NewHouseListDo> data;

    @ApiModelProperty(value = "总条数",name = "totalNum")
    private long totalNum;
}
