package com.toutiao.app.service.newhouse;

import com.toutiao.app.domain.newhouse.*;
import com.toutiao.app.domain.plot.PlotDetailsFewDomain;

import java.util.List;

public interface NewHouseRestService {

    /**
     * 根据newcode获取新房数据
     * @param newcode
     * @return
     */
    NewHouseDetailDo getNewHouseBulidByNewcode(Integer newcode);



    /**
     * 新房列表
     * @param newHouseListDo
     * @return
     */
    NewHouseListDomain getNewHouseList(NewHouseListDo newHouseListDo);

    /**
     *  新房动态
     */
    List<NewHouseDynamicDo> getNewHouseDynamicByNewCode(NewHouseDynamicDo newHouseDynamicDo);


    /**
     * 新房交通配套
     */

      NewHouseTrafficDo getNewHouseTrafficByNewCode(Integer newCode);



}
