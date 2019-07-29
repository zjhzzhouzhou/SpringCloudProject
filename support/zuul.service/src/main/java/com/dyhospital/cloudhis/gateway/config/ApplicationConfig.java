package com.dyhospital.cloudhis.gateway.config;

import com.dyhospital.cloudhis.common.utils.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author yangzongxue
 *  使用环境中的配置覆盖基本配置，如果无环境配置，则获取默认配置。
 */
@Configuration
@PropertySource(value = {
        "classpath:/config/redis.properties",
        "classpath:/config/redis-${spring.profiles.active}.properties"})
public class ApplicationConfig {
	@Autowired
	public void initUtil(ApplicationContext ctx){
		new SpringContextUtil().setApplicationContext(ctx);
	}
}
