package com.toutiao.web.apiimpl.rest.subscribe;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.web.apiimpl.rest.subscribe.api.RestApi;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 18710 on 2018/11/14.
 */
public class SubscribeTest {
    private ObjectMapper objectMapper;

    private HttpServletRequest request;
    RestApi restApi = new ConditionSubscribeRestController(objectMapper,request);

    @Test
    public void deleteConditionSubscribe(){
         Object o = restApi.deleteConditionSubscribe(3);
        System.out.println(o);
    }
}
