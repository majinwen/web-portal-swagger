package com.toutiao.web.service.repository.admin;

import com.toutiao.web.dao.entity.admin.ProjHouseInfo;
import com.toutiao.web.dao.entity.admin.VillageEntity;


public interface SaveToESService {
     Boolean saveParent( VillageEntity village);

    Boolean saveChild(ProjHouseInfo projHouseInfo1);
}
