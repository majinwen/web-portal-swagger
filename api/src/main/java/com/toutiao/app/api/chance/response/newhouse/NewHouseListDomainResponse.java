package com.toutiao.app.api.chance.response.newhouse;

import com.toutiao.app.domain.newhouse.NewHouseListDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
public class NewHouseListDomainResponse {

    private  List<NewHouseListDo> data;

    @ChangeName("totalNum")
    private long totalCount;

    @ApiModelProperty(value = "是否为猜你喜欢的数据", name = "isGuess")
    private Integer isGuess;

}
