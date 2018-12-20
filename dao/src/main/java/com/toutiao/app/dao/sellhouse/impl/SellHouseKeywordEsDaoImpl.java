package com.toutiao.app.dao.sellhouse.impl;

import com.toutiao.app.dao.sellhouse.SellHouseKeywordEsDao;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequest;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SellHouseKeywordEsDaoImpl implements SellHouseKeywordEsDao {

    @Autowired
    private RestHighLevelClient restHighLevelClient;



    @Override
    public List<String> filterKeyWords(String keywords, String city) {

        List<String> searchTermList = new ArrayList<>();

        if (StringUtil.isNotNullString(DistrictMap.getDistricts(keywords))) {
            AnalyzeRequest request = new AnalyzeRequest();
            request.analyzer("ik_smart");
            request.text(keywords);
            AnalyzeResponse response = null;
            try {
                response = restHighLevelClient.indices().analyze(request, RequestOptions.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<AnalyzeResponse.AnalyzeToken> tokens = response.getTokens();
            tokens.forEach(ikToken -> { searchTermList.add(ikToken.getTerm()); });

        } else if (StringUtil.isNotNullString(AreaMap.getAreas(keywords))) {
            AnalyzeRequest request = new AnalyzeRequest();
            request.analyzer("ik_max_word");
            request.text(keywords);
            AnalyzeResponse response = null;
            try {
                response = restHighLevelClient.indices().analyze(request, RequestOptions.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<AnalyzeResponse.AnalyzeToken> tokens = response.getTokens();
            tokens.forEach(ikToken -> { searchTermList.add(ikToken.getTerm()); });

        } else {

            AnalyzeRequest request = new AnalyzeRequest();
            request.analyzer("ik_max_word");
            request.text(keywords);
            AnalyzeResponse response = null;
            try {
                response = restHighLevelClient.indices().analyze(request, RequestOptions.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<AnalyzeResponse.AnalyzeToken> tokens = response.getTokens();
            if(keywords.length()>1){
                tokens.forEach(ikToken -> {
                    if(ikToken.getTerm().length() > 1){
                        searchTermList.add(ikToken.getTerm());
                    }
                });
            }else {
                tokens.forEach(ikToken -> { searchTermList.add(ikToken.getTerm()); });
            }
        }
        return searchTermList;
    }
}
