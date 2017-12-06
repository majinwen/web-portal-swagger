package com.toutiao.web.apiimpl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.dao.entity.admin.ProjInfo;
import com.toutiao.web.domain.query.HousingProjectQuery;
import com.toutiao.web.service.HousingProjectService;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.util.List;

/**
 * 新房楼盘管理
 * @author WuShoulei on 2017/11/15
 */
@RestController
@RequestMapping("/newHouse")
public class NewHouseHousingProjectRestful {

    @Autowired
    private HousingProjectService housingProjectService;

    /**
     * 楼盘列表-新房
     * @param query
     * @return
     */
    @RequestMapping(value= "/listHousingProject")
    @ResponseBody
    public NashResult listNewHouseHousingProject(HousingProjectQuery query) {

        query.setProjFlag(0);//新房楼盘标志位
        List<ProjInfo> projInfoList = housingProjectService.listHousingProject(query);



        try {
            // 设置集群名称
            Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
            // 创建client
            TransportClient client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.104.96.88"), 9300));

            for (int i = 0; i < projInfoList.size(); i++) {
                 ProjInfo projInfo = projInfoList.get(i);
                 String sdd = JSON.toJSONString(projInfo);
                 IndexResponse response = client.prepareIndex("proj", "proj_house",projInfo.getNewcode().toString()).setSource(sdd).get();
                 System.out.println("成功创建了一个索引，索引名为："+response.getIndex()+",类别为："+response.getType()+",文档ID为："+response.getId());
            }



            // 关闭client
            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }




        return NashResult.build(projInfoList);
    }

}
