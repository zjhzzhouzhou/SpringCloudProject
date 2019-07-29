package com.dyhospital.cloudhis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author zhouzhou
 * @date 2019-7-01 16:57:46
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources( MessageServiceApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MessageServiceApplication.class, args);
	}

}
