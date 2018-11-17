package com.toutiao.appV2.model.HomePage;

import com.toutiao.app.domain.homepage.HomePageTop50Do;
import lombok.Data;

import java.util.Map;

/**
 * Created by 18710 on 2018/11/16.
 */
@Data
public class HomePageTop50DoMap {
    Map<String,HomePageTop50Do> homePageTop50DoMap;
    Integer total;
}
