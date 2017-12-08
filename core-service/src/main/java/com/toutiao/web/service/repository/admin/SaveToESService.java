package com.toutiao.web.service.repository.admin;

import com.toutiao.web.dao.entity.admin.VillageEntity;

import java.util.Map;

public interface SaveToESService {
    public Map save(String index, String type, VillageEntity village) throws Exception;
}
