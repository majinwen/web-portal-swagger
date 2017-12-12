package com.toutiao.web.service.repository.admin;


import java.util.List;
import java.util.Map;

public interface SysBuildIndexMappingService {
     void buildIndexMapping(String index,String type) throws Exception;
}
