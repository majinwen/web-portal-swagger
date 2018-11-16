package com.toutiao.appV2.model.newhouse;

import com.toutiao.app.domain.newhouse.NewHouseLayoutDo;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author zym
 */
@Data
@Builder
public class GetNewHouseLayoutResponse {
    private List<NewHouseLayoutDo> data;

    private Integer totalNum;
}
