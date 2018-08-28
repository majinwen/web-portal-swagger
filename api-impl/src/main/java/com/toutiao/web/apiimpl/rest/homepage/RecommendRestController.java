package com.toutiao.web.apiimpl.rest.homepage;

import com.toutiao.app.api.chance.request.homepage.RecommendRequest;
import com.toutiao.app.domain.homepage.RecommendTopicDoQuery;
import com.toutiao.app.domain.homepage.RecommendTopicDomain;
import com.toutiao.app.service.homepage.RecommendRestService;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.city.CityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-08-07
 * Time:   10:58
 * Theme:
 */
@RestController
@RequestMapping("/rest/homePage/recommendTopic")
public class RecommendRestController {


    @Autowired
    private RecommendRestService recommendRestService;

    /**
     * 首页--推荐专题
     * @param recommendRequest
     * @return
     */
    @RequestMapping(value = "/queryRecommendTopic")
    @ResponseBody
    public NashResult queryRecommendTopic(RecommendRequest recommendRequest){

        RecommendTopicDoQuery recommendTopicDoQuery = new RecommendTopicDoQuery();
        BeanUtils.copyProperties(recommendRequest, recommendTopicDoQuery);

        RecommendTopicDomain recommendTopicDomain= recommendRestService.getRecommendTopic(recommendTopicDoQuery, CityUtils.getCity());

        return NashResult.build(recommendTopicDomain);
    }


}
