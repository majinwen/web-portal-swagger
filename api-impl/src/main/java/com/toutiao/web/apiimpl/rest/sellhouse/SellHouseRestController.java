package com.toutiao.web.apiimpl.rest.sellhouse;


import com.toutiao.app.api.chance.response.sellhouse.SellHouseDetailsResponse;
import com.toutiao.app.domain.sellhouse.SellHouseDetailsDo;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.app.api.chance.request.sellhouse.SellHouseDetailsRequest;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest/esf")
public class SellHouseRestController {


    @Autowired
    private SellHouseService sellHouseService;


    /**
     *  二手房详情
     * @param sellHouseDetailsRequest
     * @return
     */
    @RequestMapping("/getSellHouseByHouseId")
    @ResponseBody
    public NashResult getSellHouseByHouseId(@Validated SellHouseDetailsRequest sellHouseDetailsRequest) {
        SellHouseDetailsResponse sellHouseDetailsResponse = new SellHouseDetailsResponse();
        SellHouseDetailsDo sellHouseDetailsDo = sellHouseService.getSellHouseByHouseId(sellHouseDetailsRequest.getHouseId());
        BeanUtils.copyProperties(sellHouseDetailsDo, sellHouseDetailsResponse);
        return NashResult.build(sellHouseDetailsResponse);
    }

}
