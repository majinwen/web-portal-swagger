package com.toutiao.web.apiimpl.rest.sellhouse;

import com.toutiao.app.api.chance.request.sellhouse.LowerPriceShellHouseRequest;
import com.toutiao.app.api.chance.response.sellhouse.LowerPriceShellHouseResponse;
import com.toutiao.app.domain.sellhouse.LowerPriceShellHouseDoQuery;
import com.toutiao.app.domain.sellhouse.LowerPriceShellHouseDomain;
import com.toutiao.app.domain.subscribe.UserSubscribeDetailDo;
import com.toutiao.app.service.sellhouse.LowerPriceSellHouseRestService;
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
 * 捡漏房专题页控制器
 *
 * @author zym
 */
@RestController
@RequestMapping("/rest/esf/lowerPrice")
public class LowerPriceSellHouseRestController {
	@Autowired
	private LowerPriceSellHouseRestService lowerPriceSellHouseRestService;
	@Autowired
	private SubscribeService subscribeService;

	/**
	 * 获取捡漏房数据
	 */
	@RequestMapping(value = "/getLowerPriceShellHouse", method = RequestMethod.GET)
	@ResponseBody
	public NashResult getLowerPriceShellHouse(LowerPriceShellHouseRequest lowerPriceShellHouseRequest) {
		LowerPriceShellHouseDoQuery lowerPriceShellHouseDoQuery = new LowerPriceShellHouseDoQuery();
		BeanUtils.copyProperties(lowerPriceShellHouseRequest, lowerPriceShellHouseDoQuery);
		LowerPriceShellHouseDomain lowerPriceShellHouses = lowerPriceSellHouseRestService
				.getLowerPriceHouse(lowerPriceShellHouseDoQuery);

		//查询订阅Id
		if (!UserBasic.isLogin()) {
			lowerPriceShellHouses.setSubscriptionId(-1);
		} else {
			UserBasic userBasic = UserBasic.getCurrent();
			UserSubscribeDetailDo userSubscribeDetailDo = new UserSubscribeDetailDo();
			userSubscribeDetailDo.setTopicType(2);
			Integer areaId = lowerPriceShellHouseRequest.getAreaId();
			if (areaId != null) {
				userSubscribeDetailDo.setDistrictId(areaId);
			}
			Integer lowestTotalPrice = lowerPriceShellHouseRequest.getLowestTotalPrice();
			if (lowestTotalPrice != null) {
				userSubscribeDetailDo.setBeginPrice(lowestTotalPrice);
			}
			Integer highestTotalPrice = lowerPriceShellHouseRequest.getHighestTotalPrice();
			if (highestTotalPrice != null) {
				userSubscribeDetailDo.setEndPrice(highestTotalPrice);
			}

			UserSubscribe userSubscribe = subscribeService.selectByUserSubscribeMap(userSubscribeDetailDo, Integer
					.valueOf(userBasic.getUserId()));
			if (userSubscribe != null) {
				lowerPriceShellHouses.setSubscriptionId(userSubscribe.getId());
			} else {
				lowerPriceShellHouses.setSubscriptionId(-1);
			}
		}
		LowerPriceShellHouseResponse lowerPriceShellHouseResponse = new LowerPriceShellHouseResponse();
		BeanUtils.copyProperties(lowerPriceShellHouses, lowerPriceShellHouseResponse);
		return NashResult.build(lowerPriceShellHouses);
	}
}
