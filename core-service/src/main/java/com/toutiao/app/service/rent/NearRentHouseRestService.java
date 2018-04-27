package com.toutiao.app.service.rent;

import com.toutiao.app.domain.rent.NearHouseDo;
import com.toutiao.app.domain.rent.RentDetailsDoList;
import com.toutiao.app.domain.rent.RentDetailsFewDo;

import java.util.List;

public interface NearRentHouseRestService {
    /**
     * 附近5km内出租房源(规则:app的是吧，那就优先三公里的录入房源由近到远)
     * @param nearHouseDo
     * @return
     */
    RentDetailsDoList queryNearHouseByLocation(NearHouseDo nearHouseDo);

    /**
     * 对关键字进行分词
     * @param keywords
     * @return
     */
    List<String> getAnalyzeByKeyWords(String keywords);
}
