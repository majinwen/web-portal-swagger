package com.toutiao.web.service.intelligence.impl;


import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFindhouse;
import com.toutiao.web.dao.entity.officeweb.TotalListedRatio;
import com.toutiao.web.dao.entity.officeweb.TotalRoomRatio;
import com.toutiao.web.dao.entity.robot.QueryFindByRobot;
import com.toutiao.web.dao.entity.robot.SubwayDistance;
import com.toutiao.web.dao.mapper.officeweb.IntelligenceFhResMapper;
import com.toutiao.web.dao.mapper.officeweb.IntelligenceFindhouseMapper;
import com.toutiao.web.dao.mapper.officeweb.TotalListedRatioMapper;
import com.toutiao.web.dao.mapper.officeweb.TotalRoomRatioMapper;
import com.toutiao.web.domain.query.IntelligenceQuery;
import com.toutiao.web.service.intelligence.IntelligenceFindHouseService;
import org.apache.commons.beanutils.BeanUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
@Transactional
public class IntelligenceFindHouseServiceImpl implements IntelligenceFindHouseService {
    @Autowired
    private ESClientTools esClientTools;
    @Value("${tt.robot.index}")
    private String robotIndex;//索引名称
    @Value("${tt.robot.index}")
    private String robotType;//索引类
    @Autowired
    private TotalListedRatioMapper totalListedRatioMapper;
    @Autowired
    private TotalRoomRatioMapper totalRoomRatioMapper;
    @Autowired
    private IntelligenceFhResMapper intelligenceFhResMapper;
    @Autowired
    private IntelligenceFindhouseMapper intelligenceFindhouseMapper;

    /**
     * 功能描述：根据用户的手机号码获取用户报表相关数据信息
     *
     * @param [usePhone]
     * @return com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes
     * @author zhw
     * @date 2017/12/26 15:45
     */
    @Override
    public IntelligenceFhRes queryUserReport(String usePhone) {

        IntelligenceFhRes intelligenceFhRes = intelligenceFhResMapper.selectByUserPhone(usePhone);

        return intelligenceFhRes;
    }

    @Override
    public Map<String, String> queryUserCheckPrice(IntelligenceQuery intelligenceQuery) {
        //初始化
        Double plotTotal = null;
        //判断用户是否首付还是总价
        //如果是首付和月付 则需要计算总价  总价=首付+月供*12*30
        if (StringTool.isNotBlank(intelligenceQuery.getBeginDownPayment()) && StringTool.isNotBlank(intelligenceQuery.getBeginMonthPayment())) {
            plotTotal = intelligenceQuery.getBeginDownPayment() + intelligenceQuery.getBeginMonthPayment() * 12 * 30;
        }
        //选择总价
        if (StringTool.isNotBlank(intelligenceQuery.getPreconcTotal())) {
            plotTotal = intelligenceQuery.getBeginDownPayment() + intelligenceQuery.getBeginMonthPayment() * 12 * 30;
        }
        String userType = intelligenceQuery.getUserType();
        //判断总价是否高于400万
        if (StringTool.isNotBlank(plotTotal) && plotTotal >= 4E6) {
            //获取用户的类型
            if (StringTool.isNotBlank(userType) && userType.equalsIgnoreCase("1")) {
                //需要将用户选择的类型改成自住 改善
                userType="3";
                intelligenceQuery.setUserType(userType);
            }

        }



        return null;
    }
}
