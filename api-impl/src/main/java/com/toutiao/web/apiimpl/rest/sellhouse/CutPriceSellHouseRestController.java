package com.toutiao.web.apiimpl.rest.sellhouse;

import com.toutiao.app.api.chance.request.sellhouse.CutPriceShellHouseRequest;
import com.toutiao.app.api.chance.response.sellhouse.CutPriceShellHouseResponse;
import com.toutiao.app.domain.sellhouse.CutPriceShellHouseDoQuery;
import com.toutiao.app.domain.sellhouse.CutPriceShellHouseDomain;
import com.toutiao.app.domain.subscribe.UserSubscribeDetailDo;
import com.toutiao.app.service.sellhouse.CutPriceSellHouseRestService;
import com.toutiao.app.service.subscribe.SubscribeService;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import com.toutiao.web.dao.entity.subscribe.UserSubscribe;
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

	@Autowired
	private SubscribeService subscribeService;

	/**
	 * 专题页获取降价房List
	 */
	@RequestMapping(value = "/getCutPriceShellHouse", method = RequestMethod.GET)
	@ResponseBody
	public NashResult getCutPriceShellHouse(CutPriceShellHouseRequest cutPriceShellHouseRequest) {
		CutPriceShellHouseDoQuery cutPriceShellHouseDoQuery = new CutPriceShellHouseDoQuery();
		BeanUtils.copyProperties(cutPriceShellHouseRequest, cutPriceShellHouseDoQuery);
		CutPriceShellHouseDomain cutPriceShellHouses = cutPriceSellHouseRestService
				.getCutPriceHouse(cutPriceShellHouseDoQuery);

		//查询订阅Id
		if (!UserBasic.isLogin()) {
			cutPriceShellHouses.setSubscriptionId(-1);
		} else {
			UserBasic userBasic = UserBasic.getCurrent();
			UserSubscribeDetailDo userSubscribeDetailDo = new UserSubscribeDetailDo();
			userSubscribeDetailDo.setTopicType(1);
			Integer areaId = cutPriceShellHouseRequest.getAreaId();
			if (areaId != null) {
				userSubscribeDetailDo.setDistrictId(areaId);
			}
			Integer lowestTotalPrice = cutPriceShellHouseRequest.getLowestTotalPrice();
			if (lowestTotalPrice != null) {
				userSubscribeDetailDo.setBeginPrice(lowestTotalPrice);
			}
			Integer highestTotalPrice = cutPriceShellHouseRequest.getHighestTotalPrice();
			if (highestTotalPrice != null) {
				userSubscribeDetailDo.setEndPrice(highestTotalPrice);
			}

			UserSubscribe userSubscribe = subscribeService.selectByUserSubscribeMap(userSubscribeDetailDo, Integer
					.valueOf(userBasic.getUserId()));
			if (userSubscribe != null) {
				cutPriceShellHouses.setSubscriptionId(userSubscribe.getId());
			} else {
				cutPriceShellHouses.setSubscriptionId(-1);
			}
		}
		CutPriceShellHouseResponse cutPriceShellHouseResponse = new CutPriceShellHouseResponse();
		BeanUtils.copyProperties(cutPriceShellHouses, cutPriceShellHouseResponse);
		return NashResult.build(cutPriceShellHouses);
	}
}
