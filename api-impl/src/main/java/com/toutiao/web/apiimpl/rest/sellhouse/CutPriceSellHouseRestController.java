package com.toutiao.web.apiimpl.rest.sellhouse;

import com.toutiao.app.api.chance.request.sellhouse.CutPriceShellHouseRequest;
import com.toutiao.app.api.chance.response.sellhouse.CutPriceShellHouseResponse;
import com.toutiao.app.domain.sellhouse.CutPriceShellHouseDoQuery;
import com.toutiao.app.domain.sellhouse.CutPriceShellHouseDomain;
import com.toutiao.app.service.sellhouse.CutPriceSellHouseRestService;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 降价房专题页控制器
 *
 * @author zym
 */
@RestController
@RequestMapping("/rest/esf/cutPrice")
public class CutPriceSellHouseRestController {
	@Autowired
	private CutPriceSellHouseRestService cutPriceSellHouseRestService;

	/**
	 * 专题页获取降价房List
	 */
	@RequestMapping(value = "/getCutPriceShellHouse", method = RequestMethod.GET)
	@ResponseBody
	public NashResult getCutPriceShellHouse(CutPriceShellHouseRequest cutPriceShellHouseRequest) {
		CutPriceShellHouseDoQuery cutPriceShellHouseDoQuery = new CutPriceShellHouseDoQuery();
		BeanUtils.copyProperties(cutPriceShellHouseRequest, cutPriceShellHouseDoQuery);
		CutPriceShellHouseDomain cutPriceShellHouses = cutPriceSellHouseRestService.getCutPriceHouse(cutPriceShellHouseDoQuery);
		CutPriceShellHouseResponse cutPriceShellHouseResponse = new CutPriceShellHouseResponse();
		BeanUtils.copyProperties(cutPriceShellHouses, cutPriceShellHouseResponse);
		return NashResult.build(cutPriceShellHouses);
	}
}
