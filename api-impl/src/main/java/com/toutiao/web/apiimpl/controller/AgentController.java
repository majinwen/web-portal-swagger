package com.toutiao.web.apiimpl.controller;

import com.alibaba.fastjson.JSON;
import com.toutiao.web.common.util.Hmactest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

/**
 * @author WuShoulei on 2017/11/30
 */
@RestController
@RequestMapping("/agent")
public class AgentController {

    private String key = "123456";

    @RequestMapping(value = "/test1")
    @ResponseBody
    public String testJSON1() {
        return "Hello World!";
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public String testJSON(@RequestHeader(value = "Authorization") String sign,
                           HttpServletRequest request) {
        String q=request.getQueryString();
        String body="";
        try {
            BufferedReader br = request.getReader();
            StringBuffer sb = new StringBuffer("");
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            br.close();
            body = sb.toString();
        }
        catch (Exception e){

        }

        System.out.println("q+body:"+(q+body));
        String toutiaoSign = Hmactest.s(q+body, key);
        System.out.println("toutiaoSign:" + toutiaoSign.trim());
        if (!sign.equals(toutiaoSign)) {
            return "Sign failed!";
        }

        return JSON.toJSONString("");
    }
}
