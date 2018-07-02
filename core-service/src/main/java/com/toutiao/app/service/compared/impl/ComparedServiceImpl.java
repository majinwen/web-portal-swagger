package com.toutiao.app.service.compared.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.plot.PlotEsDao;
import com.toutiao.app.dao.sellhouse.SellHouseEsDao;
import com.toutiao.app.domain.compared.HouseComparedDetailDo;
import com.toutiao.app.domain.compared.HouseComparedListDo;
import com.toutiao.app.domain.plot.PlotDetailsDo;
import com.toutiao.app.service.compared.ComparedService;
import com.toutiao.web.dao.entity.compared.HouseCompared;
import com.toutiao.web.dao.mapper.compared.HouseComparedMapper;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.IdsQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

@Service
public class ComparedServiceImpl implements ComparedService {


    @Autowired
    HouseComparedMapper houseComparedMapper;

    @Autowired
    SellHouseEsDao sellHouseEsDao;

    @Autowired
    PlotEsDao plotEsDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return houseComparedMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(HouseCompared record) {
        return houseComparedMapper.insert(record);
    }

    /**
     * 新增对比接口
     *
     * @param record
     * @return -1:失败；0:重复；
     */
    @Override
    public int insertSelective(HouseCompared record) {
        return houseComparedMapper.insertSelective(record);
    }

    @Override
    public HouseCompared selectByPrimaryKey(Integer id) {
        return houseComparedMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(HouseCompared record) {
        return houseComparedMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(HouseCompared record) {
        return houseComparedMapper.updateByPrimaryKey(record);
    }

    @Override
    public HouseCompared selectByUserIdAndHouseId(Integer userId, String houseId) {
        return houseComparedMapper.selectByUserIdAndHouseId(userId, houseId);
    }

    @Override
    public List<HouseComparedListDo> selectTempComparedByIds(List<String> ids) {
        List<HouseComparedListDo> houseComparedListDoList = new ArrayList<>();
        Dictionary<String, HouseComparedListDo> houseComparedListDoDict = getESHouseComparedListDo(ids);
        for (String id : ids) {
            houseComparedListDoList.add(houseComparedListDoDict.get(id));
        }
        return houseComparedListDoList;
    }

    @Override
    public List<HouseComparedListDo> selectComparedByHouseCompareds(List<HouseCompared> houseCompareds) {
        List<HouseComparedListDo> houseComparedListDoList = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        for (HouseCompared houseCompared : houseCompareds) {
            ids.add(houseCompared.getHouseId());
        }
        Hashtable<String, HouseComparedListDo> houseComparedListDoDict = getESHouseComparedListDo(ids);
        for (HouseCompared houseCompared : houseCompareds) {
            if (houseComparedListDoDict.containsKey(houseCompared.getHouseId())) {
                HouseComparedListDo houseComparedListDo = houseComparedListDoDict.get(houseCompared.getHouseId());
                houseComparedListDo.setId(houseCompared.getId());
                houseComparedListDoList.add(houseComparedListDo);
            }
        }
        return houseComparedListDoList;
    }

    private Hashtable<String, HouseComparedListDo> getESHouseComparedListDo(List<String> ids) {
        Hashtable<String, HouseComparedListDo> houseComparedListDoDict = new Hashtable<>();
        IdsQueryBuilder idsQueryBuilder = new IdsQueryBuilder();
        for (String id : ids) {
            idsQueryBuilder.addIds(id);
        }
        SearchResponse searchResponse = sellHouseEsDao.getHouseByIds(idsQueryBuilder);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        for (SearchHit hit : searchHists) {
            String details = hit.getSourceAsString();
            HouseComparedListDo houseComparedListDo = JSON.parseObject(details, HouseComparedListDo.class);
            houseComparedListDo.setHouseId(hit.getId());
            houseComparedListDoDict.put(hit.getId(), houseComparedListDo);
        }
        return houseComparedListDoDict;
    }

    @Override
    public List<HouseCompared> selectByUserId(Integer userId) {
        return houseComparedMapper.selectByUserId(userId);
    }

    @Override
    public List<HouseComparedDetailDo> selectComparedDetailByHouseIds(List<String> ids) {
        List<HouseComparedDetailDo> houseComparedListDoList = new ArrayList<>();
        Hashtable<String, HouseComparedDetailDo> houseComparedDetailDoDict = getESHouseComparedDetailDo(ids);
        for (String id : ids) {
            houseComparedListDoList.add(houseComparedDetailDoDict.get(id));
        }
        return houseComparedListDoList;
    }

    private Hashtable<String, HouseComparedDetailDo> getESHouseComparedDetailDo(List<String> ids) {
        Hashtable<String, HouseComparedDetailDo> houseComparedDetailDoDict = new Hashtable<>();
        Hashtable<String, List<String>> newcodeDict = new Hashtable<>();
        IdsQueryBuilder idsQueryBuilder = new IdsQueryBuilder();
        for (String id : ids) {
            idsQueryBuilder.addIds(id);
        }
        SearchResponse searchResponse = sellHouseEsDao.getHouseByIds(idsQueryBuilder);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        for (SearchHit hit : searchHists) {
            String details = hit.getSourceAsString();
            HouseComparedDetailDo houseComparedDetailDo = JSON.parseObject(details, HouseComparedDetailDo.class);
            houseComparedDetailDo.setHouseId(hit.getId());
            houseComparedDetailDoDict.put(hit.getId(), houseComparedDetailDo);
            if (!newcodeDict.containsKey(houseComparedDetailDo.getNewcode())) {
                List<String> houseIds = new ArrayList<>();
                houseIds.add(hit.getId());
                newcodeDict.put(houseComparedDetailDo.getNewcode(), houseIds);
            } else {
                newcodeDict.get(houseComparedDetailDo.getNewcode()).add(hit.getId());
            }
        }
        idsQueryBuilder = new IdsQueryBuilder();
        for (String id : newcodeDict.keySet()) {
            idsQueryBuilder.addIds(id);
        }
        searchResponse = plotEsDao.getPlotByIds(idsQueryBuilder);
        hits = searchResponse.getHits();
        searchHists = hits.getHits();
        for (SearchHit hit : searchHists) {
            PlotDetailsDo plotDetailsDo = JSON.parseObject(hit.getSourceAsString(), PlotDetailsDo.class);
            String newcode = hit.getId();
            if (newcodeDict.containsKey(newcode)) {
                List<String> houseIds = newcodeDict.get(newcode);
                for (String houseId : houseIds) {
                    if (houseComparedDetailDoDict.containsKey(houseId)) {
                        HouseComparedDetailDo houseComparedDetailDo = houseComparedDetailDoDict.get(houseId);
                        BeanUtils.copyProperties(plotDetailsDo, houseComparedDetailDo);
                        if ("商电".equals(plotDetailsDo.getElectricSupply())) {
                            houseComparedDetailDo.setElectricFee(1.33);
                        } else {
                            houseComparedDetailDo.setElectricFee(0.48);
                        }
                        if ("商水".equals(plotDetailsDo.getWaterSupply())) {
                            houseComparedDetailDo.setWaterFee(6.00);
                        } else {
                            houseComparedDetailDo.setWaterFee(5.00);
                        }
                        if ("0".equals(plotDetailsDo.getHeatingMode())) {
                            houseComparedDetailDo.setHeatingMode("未知");
                        }
                        if ("1".equals(plotDetailsDo.getHeatingMode())) {
                            houseComparedDetailDo.setHeatingMode("集中供暖");
                        }
                        if ("2".equals(plotDetailsDo.getHeatingMode())) {
                            houseComparedDetailDo.setHeatingMode("自供暖");
                        }
                    }
                }
            }
        }
        return houseComparedDetailDoDict;
    }
}
