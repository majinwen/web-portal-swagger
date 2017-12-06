package com.toutiao.web.service;

import com.github.pagehelper.PageHelper;
import com.toutiao.web.dao.entity.admin.ProjInfo;
import com.toutiao.web.dao.mapper.admin.ProjInfoMapper;
import com.toutiao.web.domain.query.HousingProjectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author WuShoulei on 2017/11/15
 */
@Service
@Transactional
public class HousingProjectServiceImpl implements HousingProjectService {

    @Autowired
    private ProjInfoMapper projInfoMapper;




    @Override
    public List<ProjInfo> listHousingProject(HousingProjectQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        return projInfoMapper.selectProjInfoList(query);
    }


}
