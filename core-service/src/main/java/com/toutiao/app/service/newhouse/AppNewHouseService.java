package com.toutiao.app.service.newhouse;

import com.toutiao.app.domain.newhouse.NewHouseDetailDo;
import com.toutiao.app.domain.newhouse.NewHouseLayoutDo;
import com.toutiao.app.domain.newhouse.NewHouseListDo;

import java.util.List;

public interface AppNewHouseService {

     NewHouseDetailDo getNewHouseBulidByNewcode(Integer newcode);

    List<NewHouseLayoutDo> getNewHouseLayoutByNewcode(Integer newcode);

    List<NewHouseListDo> getNewHouseList(NewHouseListDo newHouseListDo);

}
