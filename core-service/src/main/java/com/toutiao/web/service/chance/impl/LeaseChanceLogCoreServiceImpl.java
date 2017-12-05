package com.toutiao.web.service.chance.impl;

import com.github.pagehelper.PageHelper;
import com.toutiao.web.service.chance.LeaseChanceCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jyl on 17/9/13.
 */
@Service
@Transactional
public class LeaseChanceLogCoreServiceImpl implements LeaseChanceCoreService {


    @Override
    public int modifyLeaseChance() {
        return 0;
    }
}
