package com.toutiao.web.apiimpl.rest.suggest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.request.suggest.SuggestRequest;
import com.toutiao.app.api.chance.response.suggest.SuggestResponse;
import com.toutiao.app.domain.suggest.SuggestDo;
import com.toutiao.app.service.suggest.SuggestService;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/suggest")
public class SuggestRestController {

    @Autowired
    private SuggestService suggestService;

    /**
     * 关键词提示
     * @param suggestRequest
     * @return
     */
    @RequestMapping("/getSuggestByKeyword")
    public NashResult getSuggestByKeyword(@Validated SuggestRequest suggestRequest){
        SuggestResponse suggestResponse = new SuggestResponse();
        SuggestDo suggest = suggestService.suggest(suggestRequest.getKeyword(), suggestRequest.getProperty());
        BeanUtils.copyProperties(suggest,suggestResponse);
        return NashResult.build(suggestResponse);
    }



}
