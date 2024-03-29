package com.toutiao.appV2.model.sellhouse;

import com.toutiao.app.domain.message.MessageSellHouseDo;
import com.toutiao.app.domain.sellhouse.SellHouseDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "MessageSellHouseResponse", description = "MessageSellHouseResponse")
public class MessageSellHouseResponse {

    //@ChangeName("data")
    @ApiModelProperty(value = "二手房列表", name = "sellHouseList")
    private List<MessageSellHouseDo> sellHouseList;

    //@ChangeName("totalNum")
    @ApiModelProperty(value = "总条数", name = "total")
    private Integer total;

}
