package com.toutiao.app.service.compared.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.plot.PlotEsDao;
import com.toutiao.app.dao.sellhouse.SellHouseEsDao;
import com.toutiao.app.domain.compared.HouseComparedDetailDo;
import com.toutiao.app.domain.compared.HouseComparedListDo;
import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteDo;
import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteDomain;
import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteListDoQuery;
import com.toutiao.app.domain.plot.PlotDetailsDo;
import com.toutiao.app.service.community.CommunityRestService;
import com.toutiao.app.service.compared.ComparedService;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.web.common.constant.syserror.SellHouseInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.dao.entity.compared.HouseCompared;
import com.toutiao.web.dao.mapper.compared.HouseComparedMapper;
import com.toutiao.web.dao.mapper.officeweb.favorite.UserFavoriteEsHouseMapper;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.IdsQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ComparedServiceImpl implements ComparedService {
    @Autowired
    private UserFavoriteEsHouseMapper userFavoriteEsHouseMapper;

    @Autowired
    HouseComparedMapper houseComparedMapper;

    @Autowired
    SellHouseEsDao sellHouseEsDao;

    @Autowired
    PlotEsDao plotEsDao;

    @Autowired
    private CommunityRestService communityRestService;

    @Autowired
    private SellHouseService sellHouseService;

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
    public HouseCompared selectByUserIdAndHouseId(Integer userId, String houseId, Integer cityId) {
        return houseComparedMapper.selectByUserIdAndHouseId(userId, houseId, cityId);
    }

    @Override
    public List<HouseComparedListDo> selectTempComparedByIds(List<String> ids, String city) {
        List<HouseComparedListDo> houseComparedListDoList = new ArrayList<>();
        Dictionary<String, HouseComparedListDo> houseComparedListDoDict = getESHouseComparedListDo(ids, city);
        for (String id : ids) {
            if(null==houseComparedListDoDict.get(id)){

            }else{
                houseComparedListDoList.add(houseComparedListDoDict.get(id));
            }
        }
        return houseComparedListDoList;
    }

    @Override
    public List<HouseComparedListDo> selectComparedByHouseCompareds(List<HouseCompared> houseCompareds, String city) {
        List<HouseComparedListDo> houseComparedListDoList = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        for (HouseCompared houseCompared : houseCompareds) {
            ids.add(houseCompared.getHouseId());
        }
        Hashtable<String, HouseComparedListDo> houseComparedListDoDict = getESHouseComparedListDo(ids, city);
        for (HouseCompared houseCompared : houseCompareds) {
            if (houseComparedListDoDict.containsKey(houseCompared.getHouseId())) {
                HouseComparedListDo houseComparedListDo = houseComparedListDoDict.get(houseCompared.getHouseId());
                houseComparedListDo.setId(houseCompared.getId());
                houseComparedListDoList.add(houseComparedListDo);
            }
        }
        return houseComparedListDoList;
    }

    private Hashtable<String, HouseComparedListDo> getESHouseComparedListDo(List<String> ids, String city) {
        Date date = new Date();
        Hashtable<String, HouseComparedListDo> houseComparedListDoDict = new Hashtable<>();
        IdsQueryBuilder idsQueryBuilder = new IdsQueryBuilder();
        for (String id : ids) {
            idsQueryBuilder.addIds(id);
        }
        SearchResponse searchResponse = sellHouseEsDao.getComparedHouseByIds(idsQueryBuilder, city);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        for (SearchHit hit : searchHists) {
            String details = hit.getSourceAsString();
            Object import_time = hit.getSource().get("import_time");
            HouseComparedListDo houseComparedListDo = JSON.parseObject(details, HouseComparedListDo.class);

            int times = sellHouseService.isDefaultImage((String)import_time,date,houseComparedListDo.getHousePhotoTitle());
            if(times==1){
                houseComparedListDo.setIsDefaultImage(1);
            }

            houseComparedListDo.setHouseId(hit.getId());
            houseComparedListDoDict.put(hit.getId(), houseComparedListDo);
        }
        return houseComparedListDoDict;
    }

    @Override
    public List<HouseCompared> selectByUserId(Integer userId, Integer cityId) {
        return houseComparedMapper.selectByUserId(userId, cityId);
    }

    @Override
    public List<HouseComparedDetailDo> selectComparedDetailByHouseIds(List<String> ids, String city) {
        List<HouseComparedDetailDo> houseComparedListDoList = new ArrayList<>();
        Hashtable<String, HouseComparedDetailDo> houseComparedDetailDoDict = getESHouseComparedDetailDo(ids,city);
        for (String id : ids) {
            HouseComparedDetailDo houseComparedDetailDo = new HouseComparedDetailDo();
            if (houseComparedDetailDoDict.containsKey(id)) {
                houseComparedDetailDo = houseComparedDetailDoDict.get(id);
                houseComparedDetailDo.setStatus(1);
            } else {
                houseComparedDetailDo.setHouseId(id);
                houseComparedDetailDo.setStatus(0);
            }
            houseComparedListDoList.add(houseComparedDetailDo);
        }
        return houseComparedListDoList;
    }

    @Override
    public SellHouseFavoriteDomain queryComparedList(SellHouseFavoriteListDoQuery sellHouseFavoriteListDoQuery) {
        SellHouseFavoriteDomain sellHouseFavoriteDomain = new SellHouseFavoriteDomain();
        sellHouseFavoriteListDoQuery.setFrom((sellHouseFavoriteListDoQuery.getPageNum()-1)*sellHouseFavoriteListDoQuery.getSize());
        sellHouseFavoriteListDoQuery.setCityId(CityUtils.returnCityId(CityUtils.getCity()));
        List<SellHouseFavoriteDo> sellHouseFavoriteDos = userFavoriteEsHouseMapper.selectComparedList(sellHouseFavoriteListDoQuery);
        if(null!=sellHouseFavoriteDos && sellHouseFavoriteDos.size()>0){
            sellHouseFavoriteDomain.setData(sellHouseFavoriteDos);
        }else{
            throw new BaseException(SellHouseInterfaceErrorCodeEnum.ESF_FAVORITE_NOT_FOUND,"比对列表为空");
        }
        return sellHouseFavoriteDomain;
    }

    private Hashtable<String, HouseComparedDetailDo> getESHouseComparedDetailDo(List<String> ids, String city) {
        Date date = new Date();
        Hashtable<String, HouseComparedDetailDo> houseComparedDetailDoDict = new Hashtable<>();
        Hashtable<String, List<String>> newcodeDict = new Hashtable<>();
        IdsQueryBuilder idsQueryBuilder = new IdsQueryBuilder();
        for (String id : ids) {
            idsQueryBuilder.addIds(id);
        }
        SearchResponse searchResponse = sellHouseEsDao.getComparedHouseByIds(idsQueryBuilder,city);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        for (SearchHit hit : searchHists) {
            String details = hit.getSourceAsString();
            HouseComparedDetailDo houseComparedDetailDo = JSON.parseObject(details, HouseComparedDetailDo.class);
            houseComparedDetailDo.setTypeCounts(communityRestService.getCountByBuildTags(CityUtils.returnCityId(city)));
            houseComparedDetailDo.setHouseId(hit.getId());
            houseComparedDetailDoDict.put(hit.getId(), houseComparedDetailDo);
            Object import_time = hit.getSource().get("import_time");
            int times = sellHouseService.isDefaultImage((String)import_time,date,houseComparedDetailDo.getHousePhotoTitle());
            if(times==1){
                houseComparedDetailDo.setIsDefaultImage(1);
            }
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
        searchResponse = plotEsDao.getPlotByIds(idsQueryBuilder, city);
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
                        houseComparedDetailDo.setRingRoadDistance(plotDetailsDo.getRingRoadDistance());
                        houseComparedDetailDo.setRingRoadName(plotDetailsDo.getRingRoadName());
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
