package com.toutiao.web.apiimpl.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@Configuration
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .host("localhost:8085")
//                .host("app.dev.bidewu.com/searchapiv2")
                .select()
                //为controller包路径
                .apis(RequestHandlerSelectors.basePackage("com.toutiao.appV2.apiimpl"))
                .paths(PathSelectors.any())
                .build();
    }

    //构建 api文档的详细信息函数
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("懂房帝APP接口文档")
                //创建人
                .contact(new Contact("懂房帝", "http://app.dev.bidewu.com/searchapiv2/swagger-ui.html", "123456@dongfangdi.com"))
                //版本号
                .version("v1")
                //描述
                .description("接口文档")
                .build();
    }
}