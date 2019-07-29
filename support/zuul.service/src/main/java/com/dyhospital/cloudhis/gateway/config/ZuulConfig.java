package com.dyhospital.cloudhis.gateway.config;

import com.dyhospital.cloudhis.gateway.filter.AccessFilter;
import com.dyhospital.cloudhis.gateway.filter.ErrorFilter;
import com.dyhospital.cloudhis.gateway.filter.PostFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author yangzongxue
 */
@Configuration
public class ZuulConfig {
    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }

    @Bean
    public ErrorFilter errorFilter(){
        return new ErrorFilter();
    }

    @Bean
    public PostFilter postFilter(){
        return new PostFilter();
    }
}
