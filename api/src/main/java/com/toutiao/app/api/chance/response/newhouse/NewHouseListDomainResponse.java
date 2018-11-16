package com.toutiao.app.api.chance.response.newhouse;

import com.toutiao.app.domain.newhouse.NewHouseListDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

import java.util.List;
@Data
public class NewHouseListDomainResponse {

    private  List<NewHouseListDo> data;

    @ChangeName("totalNum")
    private long totalCount;

}
