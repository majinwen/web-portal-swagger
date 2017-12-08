package com.toutiao.web.service.repository.admin.impl;

import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.dao.entity.admin.village;
import com.toutiao.web.service.repository.admin.SaveToES;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class SaveToESImpl implements SaveToES {
    @Override
    public Map save(String index, String type, village village) throws Exception {
        Settings settings = Settings.builder().put("cluster.name", "elasticsearch")
                .build();
        Client client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.104.96.88"), 9300));
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        Map resultMap = new HashMap();
//        village village = new village();
//        village.setId(001);
//        village.setRc("一线");
//        String[] p = {"https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=false&word=%E7%BE%8E%E5%A5%B3&hs=2&pn=0&spn=0&di=83662111520&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=3878847766%2C3988120331&os=1532124407%2C2118980604&simid=0%2C0&adpicid=0&lpn=0&ln=30&fr=ala&fm=&sme=&cg=girl&bdtype=13&oriquery=%E7%BE%8E%E5%A5%B3&objurl=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F728da9773912b31bc2fe74138d18367adab4e17e.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bi7tp7_z%26e3Bv54AzdH3Fri5p5AzdH3Ffi5oAzdH3Fda8ma0d9AzdH3F8cda9c9l0caa_z%26e3Bip4s&gsm=0","https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=false&word=%E7%BE%8E%E5%A5%B3&hs=2&pn=1&spn=0&di=70374413140&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=358796479%2C4085336161&os=1506029983%2C2823871901&simid=0%2C0&adpicid=0&lpn=0&ln=30&fr=ala&fm=&sme=&cg=girl&bdtype=13&oriquery=%E7%BE%8E%E5%A5%B3&objurl=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fe1fe9925bc315c600dce09d386b1cb13495477b6.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Frwtxtg_z%26e3Bv54AzdH3Fri5p5v5ry6t2ipAzdH3F89d0c90dl&gsm=0"};
//        village.setPhoto(p);
//        village.setAlias("一线");
//        village.setAreaId("001");
//        village.setArea("朝阳");
//        village.setTradingareaId("001");
//        village.setTradingarea("大悦城");
//        village.setAddress("北京市朝阳区四惠远洋大厦");
//        Double[] l = {0.0,0.0};
//        village.setLocation(l);
//        String[] mId ={"001","002"};
//        village.setMetroStationId(mId);
//        String[] m ={"四惠站","四惠东站"};
//        village.setMetroStation(m);
//        village.setAvgprice("69999");
//        village.setAge("15");
//        village.setElevator("是");
//        village.setDesc("哈哈哈哈哈哈哈哈哈");
//        village.setDevelopers("中国二建");
//        village.setYopr("70");

//        village village = new village();
//        village.setId(002);
//        village.setRc("二线");
//        String[] p = {"https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=false&word=%E7%BE%8E%E5%A5%B3&hs=2&pn=0&spn=0&di=83662111520&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=3878847766%2C3988120331&os=1532124407%2C2118980604&simid=0%2C0&adpicid=0&lpn=0&ln=30&fr=ala&fm=&sme=&cg=girl&bdtype=13&oriquery=%E7%BE%8E%E5%A5%B3&objurl=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F728da9773912b31bc2fe74138d18367adab4e17e.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bi7tp7_z%26e3Bv54AzdH3Fri5p5AzdH3Ffi5oAzdH3Fda8ma0d9AzdH3F8cda9c9l0caa_z%26e3Bip4s&gsm=0","https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=false&word=%E7%BE%8E%E5%A5%B3&hs=2&pn=1&spn=0&di=70374413140&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=358796479%2C4085336161&os=1506029983%2C2823871901&simid=0%2C0&adpicid=0&lpn=0&ln=30&fr=ala&fm=&sme=&cg=girl&bdtype=13&oriquery=%E7%BE%8E%E5%A5%B3&objurl=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fe1fe9925bc315c600dce09d386b1cb13495477b6.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Frwtxtg_z%26e3Bv54AzdH3Fri5p5v5ry6t2ipAzdH3F89d0c90dl&gsm=0"};
//        village.setPhoto(p);
//        village.setAlias("二线");
//        village.setAreaId("002");
//        village.setArea("海淀");
//        village.setTradingareaId("002");
//        village.setTradingarea("中悦城");
//        village.setAddress("北京市海淀区四惠远洋大厦");
//        Double[] l = {20.0,20.0};
//        village.setLocation(l);
//        String[] mId ={"003","004"};
//        village.setMetroStationId(mId);
//        String[] m ={"五惠站","五惠东站"};
//        village.setMetroStation(m);
//        village.setAvgprice("79999");
//        village.setAge("16");
//        village.setElevator("是");
//        village.setDesc("哈哈哈哈哈哈哈哈哈");
//        village.setDevelopers("中国三建");
//        village.setYopr("70");
//        JSONObject  json = (JSONObject) JSONObject.toJSON(village);


        village.setId(003);
        village.setRc("三线");
        String[] p = {"https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=false&word=%E7%BE%8E%E5%A5%B3&hs=2&pn=0&spn=0&di=83662111520&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=3878847766%2C3988120331&os=1532124407%2C2118980604&simid=0%2C0&adpicid=0&lpn=0&ln=30&fr=ala&fm=&sme=&cg=girl&bdtype=13&oriquery=%E7%BE%8E%E5%A5%B3&objurl=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F728da9773912b31bc2fe74138d18367adab4e17e.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bi7tp7_z%26e3Bv54AzdH3Fri5p5AzdH3Ffi5oAzdH3Fda8ma0d9AzdH3F8cda9c9l0caa_z%26e3Bip4s&gsm=0","https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=false&word=%E7%BE%8E%E5%A5%B3&hs=2&pn=1&spn=0&di=70374413140&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=358796479%2C4085336161&os=1506029983%2C2823871901&simid=0%2C0&adpicid=0&lpn=0&ln=30&fr=ala&fm=&sme=&cg=girl&bdtype=13&oriquery=%E7%BE%8E%E5%A5%B3&objurl=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fe1fe9925bc315c600dce09d386b1cb13495477b6.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Frwtxtg_z%26e3Bv54AzdH3Fri5p5v5ry6t2ipAzdH3F89d0c90dl&gsm=0"};
        village.setPhoto(p);
        village.setAlias("三线");
        village.setAreaId("003");
        village.setArea("丰台");
        village.setTradingareaId("003");
        village.setTradingarea("小悦城");
        village.setAddress("北京市丰台区四惠远洋大厦");
        Double[] l = {30.0,30.0};
        village.setLocation(l);
        String[] mId ={"005","006"};
        village.setMetroStationId(mId);
        String[] m ={"六惠站","六惠东站"};
        village.setMetroStation(m);
        village.setAvgprice("89999");
        village.setAge("17");
        village.setElevator("是");
        village.setDesc("哈哈哈哈哈哈哈哈哈");
        village.setDevelopers("中国四建");
        village.setYopr("70");
        JSONObject json = (JSONObject) JSONObject.toJSON(village);

//        IndexRequestBuilder irb = client.prepareIndex(index,
//                type, "1");
//        XContentBuilder xb = XContentFactory.jsonBuilder()
//                .startObject();
//        xb.latlon("location", 0, 0);
//        for (Object object : listData) {
//            JSONObject json = JSONObject.fromObject(object);
//            没有指定idName 那就让Elasticsearch自动生成
        if(StringUtils.isBlank("")){
            IndexRequestBuilder lrb = client
                    .prepareIndex(index, type)
                    .setSource(json);
            bulkRequest.add(lrb);
        }
        else{
//                String idValue = json.optString(idName);
            IndexRequestBuilder lrb = client
                    .prepareIndex(index, type,"003")
                    .setSource(json);
            bulkRequest.add(lrb);
        }
//        IndexRequestBuilder lrb = client
//                        .prepareIndex(index, type)
//                        .setSource(json);
//        bulkRequest.add(lrb);
////
//        }
//        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
//        if (bulkResponse.hasFailures()) {
//            // process failures by iterating through each bulk response item
//            System.out.println(bulkResponse.getItems().toString());
//            resultMap.put("500", "保存ES失败!");
//            return resultMap;
//        }
//        bulkRequest = ESTools.client.prepareBulk();
//        resultMap.put("200", "保存ES成功!");
//        xb.endObject();
//        irb.setSource(xb);
//        BulkResponse bulkResponse = brb.execute().actionGet();
        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
        return null;
    }
}
