package com.toutiao.web.service.map.impl;

import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.dao.entity.officeweb.MapInfo;
import com.toutiao.web.dao.mapper.officeweb.MapInfoMapper;
import com.toutiao.web.service.map.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapServiceImpl implements MapService {
    @Autowired
    private MapInfoMapper mapInfoMapper;
   @Override
    public MapInfo getMapInfo(String newcode){

        MapInfo mapInfo= null;
        try {
            mapInfo = mapInfoMapper.selectByNewCode(newcode);
            if (StringTool.isNotBlank(mapInfo)){
                return mapInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
