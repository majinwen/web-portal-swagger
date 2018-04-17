package com.toutiao.app.service.newhouse;

import com.toutiao.app.domain.newhouse.NewHouseDetailDo;
import com.toutiao.app.domain.newhouse.NewHouseLayoutDo;
import com.toutiao.app.domain.newhouse.NewHouseListDo;
import com.toutiao.app.domain.newhouse.NewHouseListDomain;

import java.util.List;

public interface NewHouseRestService {

    /**
     * 根据newcode获取新房数据
     * @param newcode
     * @return
     */
    NewHouseDetailDo getNewHouseBulidByNewcode(Integer newcode);

    /**
     * 根据newcode获取户型信息
     * @param newcode
     * @return
     */
    List<NewHouseLayoutDo> getNewHouseLayoutByNewcode(Integer newcode);

    NewHouseListDomain getNewHouseList(NewHouseListDo newHouseListDo);

}
