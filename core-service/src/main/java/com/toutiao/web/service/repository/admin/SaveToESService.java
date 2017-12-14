package com.toutiao.web.service.repository.admin;

import com.toutiao.web.dao.entity.admin.VillageEntity;


public interface SaveToESService {
     Boolean save(String index, String type, VillageEntity village);
}
