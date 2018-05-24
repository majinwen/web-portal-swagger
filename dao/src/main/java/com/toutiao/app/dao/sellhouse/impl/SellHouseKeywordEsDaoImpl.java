package com.toutiao.app.dao.sellhouse.impl;

import com.toutiao.app.dao.sellhouse.SellHouseKeywordEsDao;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeAction;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellHouseKeywordEsDaoImpl implements SellHouseKeywordEsDao {

    @Autowired
    private ESClientTools esClientTools;
    @Value("${tt.projhouse.index}")
    private String projhouseIndex;//索引名称
    @Value("${tt.projhouse.type}")
    private String projhouseType;//索引类


    @Override
    public List<String> filterKeyWords(String keywords) {


        List<String> searchTermList = new ArrayList<>();

        TransportClient client = esClientTools.init();
        if (StringUtil.isNotNullString(DistrictMap.getDistricts(keywords))) {
            AnalyzeRequestBuilder ikRequest = new AnalyzeRequestBuilder(client, AnalyzeAction.INSTANCE,projhouseIndex,keywords);
            ikRequest.setTokenizer("ik_smart");
            List<AnalyzeResponse.AnalyzeToken> ikTokenList = ikRequest.execute().actionGet().getTokens();
            ikTokenList.forEach(ikToken -> { searchTermList.add(ikToken.getTerm()); });

        } else if (StringUtil.isNotNullString(AreaMap.getAreas(keywords))) {
            AnalyzeRequestBuilder ikRequest = new AnalyzeRequestBuilder(client, AnalyzeAction.INSTANCE,projhouseIndex,keywords);
            ikRequest.setTokenizer("ik_max_word");
            List<AnalyzeResponse.AnalyzeToken> ikTokenList = ikRequest.execute().actionGet().getTokens();
            ikTokenList.forEach(ikToken -> { searchTermList.add(ikToken.getTerm()); });
        } else {

            AnalyzeRequestBuilder ikRequest = new AnalyzeRequestBuilder(client, AnalyzeAction.INSTANCE,projhouseIndex,keywords);
            ikRequest.setTokenizer("ik_max_word");
            List<AnalyzeResponse.AnalyzeToken>
                    ikTokenList = ikRequest.execute().actionGet().getTokens();
            if(keywords.length()>1){
                ikTokenList.forEach(ikToken -> {
                    if(ikToken.getTerm().length() > 1){
                        searchTermList.add(ikToken.getTerm());
                    }
                });
            } else {
                ikTokenList.forEach(ikToken -> { searchTermList.add(ikToken.getTerm()); });
            }
        }
        return searchTermList;
    }
}
