package com.toutiao.app.service.newhouse.impl;
import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.Appnewhouse.AppNewHouseEsDao;
import com.toutiao.app.domain.newhouse.NewHouseDetailDo;
import com.toutiao.app.domain.newhouse.NewHouseLayoutDo;
import com.toutiao.app.domain.sellhouse.SellHouseDetailsDo;
import com.toutiao.app.service.newhouse.AppNewHouseService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AppNewHouseServiceImpl implements AppNewHouseService {

    @Autowired
    private AppNewHouseEsDao newHouseEsDao;


    /**
     * 根据newcode获取新房详细信息
     * @param newcode
     * @return
     */
    @Override
    public NewHouseDetailDo getNewHouseBulidByNewcode(Integer newcode) {
        //查询新房信息
        SearchResponse bulidResponse =newHouseEsDao.getNewHouseBulidByNewCode(newcode);
        SearchHits hits = bulidResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        String details = "";
        for (SearchHit searchHit : searchHists) {
            details = searchHit.getSourceAsString();
        }
        NewHouseDetailDo newHouseDetailDo = JSON.parseObject(details,NewHouseDetailDo.class);
        return  newHouseDetailDo;

    }


    /**
     *
     * @param newcode
     * 根据newcode查询户型信息
     * @return
     */
    @Override
    public List<NewHouseLayoutDo> getNewHouseLayoutByNewcode(Integer newcode) {

        List<NewHouseLayoutDo> newHouseLayoutDos=new ArrayList<>();
        SearchResponse layoutResponse =newHouseEsDao.getNewHouseLayoutByNewCode(newcode);
        SearchHits hits = layoutResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        for (SearchHit searchHit : searchHists) {
            String details = "";
            details=searchHit.getSourceAsString();
            NewHouseLayoutDo newHouseLayoutDo=JSON.parseObject(details,NewHouseLayoutDo.class);
            newHouseLayoutDos.add(newHouseLayoutDo);
        }
        return newHouseLayoutDos;


    }



//
//    private static Object convertMap(Class type, Map map)
//            throws IntrospectionException, IllegalAccessException,
//            InstantiationException, InvocationTargetException {
//        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
//        Object obj = type.newInstance(); // 创建 JavaBean 对象
//
//        // 给 JavaBean 对象的属性赋值
//        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
//        for (int i = 0; i < propertyDescriptors.length; i++) {
//            PropertyDescriptor descriptor = propertyDescriptors[i];
//            String propertyName = descriptor.getName();
//
//            if (map.containsKey(propertyName)) {
//                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
//                Object value = map.get(propertyName);
//
//                Object[] args = new Object[1];
//                args[0] = value;
//
//                descriptor.getWriteMethod().invoke(obj, args);
//            }
//        }
//        return obj;
//
//    }
//
//
//
//
//
//








}
