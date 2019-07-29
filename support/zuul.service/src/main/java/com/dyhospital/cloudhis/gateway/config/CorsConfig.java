package com.dyhospital.cloudhis.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

/**
 * C - Cross
 * O - Origin
 * R - Resource
 * S - Sharing
 * 跨域资源共享
 *
 * 跨域其他解决方案：
 * 1. nginx
 * 2. ajax跨域完全讲解，通用跨域解决方案
 * @author yangzongxue
 */
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setAllowedMethods(Collections.singletonList("*"));
        // 缓存时间(秒),在这个时间段里对于相同的跨域请求就不再进行检查了
        config.setMaxAge(300L);

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
