package com.toutiao.web.apiimpl.rest.newhouse;

import com.toutiao.app.api.chance.request.newhouse.NewHouseListRequest;
import com.toutiao.app.api.chance.response.newhouse.NewHouseListDomainResponse;
import com.toutiao.app.domain.newhouse.NewHouseDoQuery;
import com.toutiao.app.domain.newhouse.NewHouseListDomain;
import com.toutiao.app.service.newhouse.NewHouseTopicsRestService;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 五环最美新房专题
 *
 */
@RestController
@RequestMapping("/rest/newhouse/topic")
public class NewHouseTopicsRestController {

    @Autowired
    private NewHouseTopicsRestService newHouseTopicsRestService;


    @ResponseBody
    @RequestMapping(value = "/getNewHouseTopic",method = RequestMethod.GET)
    public NashResult getNewHouseTopic(@Validated NewHouseListRequest newHouseListRequest) {

        NewHouseListDomainResponse newHouseListDomainResponse = new NewHouseListDomainResponse();
        NewHouseDoQuery newHouseDoQuery=new NewHouseDoQuery();
        BeanUtils.copyProperties(newHouseListRequest,newHouseDoQuery);
        NewHouseListDomain newHouseListDomain = newHouseTopicsRestService.getNewHouseTopic(newHouseDoQuery);
        BeanUtils.copyProperties(newHouseListDomain,newHouseListDomainResponse);
        return  NashResult.build(newHouseListDomainResponse);
    }


}
