package com.toutiao.app.domain.hotplot;

import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-08-22
 * Time:   17:43
 * Theme:
 */
@Data
public class SearchHotProjDomain {

    private List<SearchHotProjDo> data;
}
