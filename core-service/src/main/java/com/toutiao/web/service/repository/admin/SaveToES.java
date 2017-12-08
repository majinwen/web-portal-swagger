package com.toutiao.web.service.repository.admin;

import com.toutiao.web.dao.entity.admin.village;

import java.util.Map;

public interface SaveToES {
    public Map save(String index, String type, village village) throws Exception;
}
