package com.toutiao.app.service.sellhouse;

import com.toutiao.app.domain.sellhouse.HouseBusinessAndRoomDoQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;

public interface FilterBusinessRoomChooseService {
    BoolQueryBuilder filterBusinessRoomChoose(HouseBusinessAndRoomDoQuery houseBusinessAndRoomDoQuery);

    BoolQueryBuilder filterBusinessRoomAveragePriceChoose(HouseBusinessAndRoomDoQuery houseBusinessAndRoomDoQuery);
}
