package com.toutiao.app.api.chance.response.plot;

import com.toutiao.app.domain.plot.PlotsThemeDo;
import lombok.Data;

import java.util.List;

@Data
public class PlotsThemeResponse {
	/**
	 * 小区主题List
	 */
	private List<PlotsThemeDo> data;

	/**
	 * 查询总数
	 */
	private long totalCount;
}
