package com.toutiao.web.service.repository.admin;

import com.toutiao.web.dao.entity.admin.ProjHouseInfo;
import com.toutiao.web.dao.entity.admin.VillageEntityES;


public interface SaveToESService {
     void saveParent(VillageEntityES village);

    void saveChild(ProjHouseInfo projHouseInfo1);
}
