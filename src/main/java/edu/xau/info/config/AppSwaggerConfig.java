package edu.xau.info.config;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: 杨斌
 * @Date: 2020/6/1 0001 10:02
 */
@EnableSwagger2
@SpringBootConfiguration
public class AppSwaggerConfig {

    @Value("${swagger2.enable}")
    private boolean enable;

    @Bean("项目模块")
    public Docket projectApis() {
        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("项目模块")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .enable(enable);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("实验室管理系统接口文档")
                .version("1.8")
                .build();
    }
}
