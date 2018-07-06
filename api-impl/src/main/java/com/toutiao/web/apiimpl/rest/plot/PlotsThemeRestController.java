package com.toutiao.web.apiimpl.rest.plot;

import com.toutiao.app.api.chance.request.plot.PlotsThemeRequest;
import com.toutiao.app.api.chance.response.plot.PlotsThemeResponse;
import com.toutiao.app.domain.plot.PlotsThemeDoQuery;
import com.toutiao.app.domain.plot.PlotsThemeDomain;
import com.toutiao.app.service.plot.PlotsThemeRestService;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 小区专题落地页控制器
 * 1.公园主题
 * 2.首置、改善、别墅、豪宅
 */
@RestController
@RequestMapping("/rest/plot/theme")
public class PlotsThemeRestController {
	@Autowired
	private PlotsThemeRestService plotsThemeRestService;

	@RequestMapping(value = "/getPlotsTheme", method = RequestMethod.GET)
	@ResponseBody
	public NashResult getPlotsTheme(PlotsThemeRequest plotsThemeRequest) {
		PlotsThemeDoQuery plotsThemeDoQuery = new PlotsThemeDoQuery();
		BeanUtils.copyProperties(plotsThemeRequest, plotsThemeDoQuery);
		PlotsThemeDomain plotsThemeDos = plotsThemeRestService.getPlotsThemeList(plotsThemeDoQuery);
		PlotsThemeResponse plotsThemeResponse = new PlotsThemeResponse();
		BeanUtils.copyProperties(plotsThemeDos, plotsThemeResponse);
		return NashResult.build(plotsThemeResponse);

	}
}
