package com.toutiao.app.service.compared;

import com.toutiao.app.domain.compared.HouseComparedDetailDo;
import com.toutiao.app.domain.compared.HouseComparedListDo;
import com.toutiao.app.domain.sellhouse.SellHouseSearchDomain;
import com.toutiao.web.dao.entity.compared.HouseCompared;

import java.util.Dictionary;
import java.util.List;

public interface ComparedService {
    int deleteByPrimaryKey(Integer id);

    int insert(HouseCompared record);

    int insertSelective(HouseCompared record);

    HouseCompared selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HouseCompared record);

    int updateByPrimaryKey(HouseCompared record);

    HouseCompared selectByUserIdAndHouseId(Integer userId, String houseId);

    List<HouseComparedListDo> selectTempComparedByIds(List<String> ids);

    List<HouseComparedListDo> selectComparedByHouseCompareds(List<HouseCompared> houseCompareds);

    List<HouseCompared> selectByUserId (Integer userId);

    List<HouseComparedDetailDo> selectComparedDetailByHouseIds (List<String> ids);
}
