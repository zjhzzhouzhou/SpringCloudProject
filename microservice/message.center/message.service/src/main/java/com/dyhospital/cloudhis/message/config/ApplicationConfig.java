package com.dyhospital.cloudhis.message.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author zhouzhou
 * @date 2019-7-01 16:47:01
 * 使用环境中的配置覆盖基本配置，如果无环境配置，则获取默认配置。
 */
@Configuration
@PropertySource(value = {"classpath:/config/jdbc.properties",
        "classpath:/config/jdbc-${spring.profiles.active}.properties",
        "classpath:/config/redis.properties",
        "classpath:/config/redis-${spring.profiles.active}.properties"})
public class ApplicationConfig {

}
