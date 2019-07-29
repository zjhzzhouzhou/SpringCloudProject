package com.dyhospital.cloudhis.common.web.config.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * SwaggerConfig
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    protected Environment env;


    /**
     * 可以定义多个组
     *
     */
    @Bean
    public Docket createDocket() {
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        ticketPar.name("x-access-token").description("user token 用户令牌")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build(); //header中的ticket参数非必填，传空也可以
        pars.add(ticketPar.build());

        Docket docket = new Docket(DocumentationType.SWAGGER_2).globalOperationParameters(pars)
            .apiInfo(apiInfo())
            .forCodeGeneration(true)
            .directModelSubstitute(java.nio.ByteBuffer.class, String.class)
            .select()
            .paths(PathSelectors.any())
            .build();

        return docket;

    }


    private ApiInfo apiInfo() {
        String appName = env.getProperty("spring.application.name");
        if(appName ==null){
            appName="Demo rest api";
        }

        return new ApiInfoBuilder()
                .title(appName)//大标题
                .description(appName + " RESTful API Document")//详细描述
                .version("1.0")//版本
                .termsOfServiceUrl("东阳云His Co., Ltd.")
                .contact(new Contact("东阳云His Co., Ltd.", "", "13588069316@163.com"))//作者
                .license("The Apache License, Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }

}