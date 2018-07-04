package com.toutiao.web.apiimpl.rest.sellhouse;

import com.toutiao.app.api.chance.request.sellhouse.LowerPriceShellHouseRequest;
import com.toutiao.app.api.chance.response.sellhouse.LowerPriceShellHouseResponse;
import com.toutiao.app.domain.sellhouse.LowerPriceShellHouseDoQuery;
import com.toutiao.app.domain.sellhouse.LowerPriceShellHouseDomain;
import com.toutiao.app.service.sellhouse.LowerPriceSellHouseRestService;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 捡漏房专题页控制器
 *
 * @author zym
 */
@RestController
@RequestMapping("/rest/esf/lowerPrice")
public class LowerPriceSellHouseRestController {
	@Autowired
	private LowerPriceSellHouseRestService lowerPriceSellHouseRestService;

	/**
	 * 获取捡漏房数据
	 */
	@RequestMapping(value = "/getLowerPriceShellHouse", method = RequestMethod.GET)
	@ResponseBody
	public NashResult getLowerPriceShellHouse(LowerPriceShellHouseRequest lowerPriceShellHouseRequest) {
		LowerPriceShellHouseDoQuery lowerPriceShellHouseDoQuery = new LowerPriceShellHouseDoQuery();
		BeanUtils.copyProperties(lowerPriceShellHouseRequest, lowerPriceShellHouseDoQuery);
		LowerPriceShellHouseDomain lowerPriceShellHouses = lowerPriceSellHouseRestService.getLowerPriceHouse(lowerPriceShellHouseDoQuery);
		LowerPriceShellHouseResponse lowerPriceShellHouseResponse = new LowerPriceShellHouseResponse();
		BeanUtils.copyProperties(lowerPriceShellHouses, lowerPriceShellHouseResponse);
		return NashResult.build(lowerPriceShellHouses);
	}
}
