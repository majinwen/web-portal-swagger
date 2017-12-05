package com.toutiao.web.annotation.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DruidConfig {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${toutiao.database.url}")
    private String dbUrl;

    @Value("${toutiao.database.user}")
    private String username;

    @Value("${toutiao.database.password}")
    private String password;

    @Value("${toutiao.database.driverClass}")
    private String driverClassName;

    @Value("${toutiao.database.dbIinitialSize}")
    private int initialSize;

    @Value("${toutiao.database.dbMinIdle}")
    private int minIdle;

    @Value("${toutiao.database.dbMaxActive}")
    private int maxActive;

    @Value("${toutiao.database.maxWait}")
    private int maxWait;

    @Value("${toutiao.database.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${toutiao.database.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${toutiao.database.validationQuery}")
    private String validationQuery;

    @Value("${toutiao.database.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${toutiao.database.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${toutiao.database.testOnReturn}")
    private boolean testOnReturn;

    @Value("${toutiao.database.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${toutiao.database.filters}")
    private String filters;

//    @Bean
//    public ServletRegistrationBean druidServlet() {
//        ServletRegistrationBean reg = new ServletRegistrationBean();
//        reg.setServlet(new StatViewServlet());
//        reg.addUrlMappings("/druid/*");
//        reg.addInitParameter("loginUsername", "druid");
//        reg.addInitParameter("loginPassword", "jiajian123456");
//        return reg;
//    }
//
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new WebStatFilter());
//        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
//        filterRegistrationBean.addInitParameter("profileEnable", "true");
//        filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
//        filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");
//        return filterRegistrationBean;
//    }

    @Bean
    public DataSource druidDataSource(){
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
//        WallConfig wallConfig = new WallConfig();
//        wallConfig.setDir("META-INF/druid/wall/postgre");
//        WallFilter wallFilter = new WallFilter();
//        wallFilter.setDbType("postgresql");
//        wallFilter.setConfig(wallConfig);
//        try {
//            List<Filter> wallFilters = new ArrayList<>();
//            wallFilters.add(wallFilter);
//            datasource.setProxyFilters(wallFilters);
//            datasource.setFilters(filters);
//        } catch (Exception e) {
//            logger.error("druid configuration initialization filter", e);
//        }
        return datasource;
    }
}
