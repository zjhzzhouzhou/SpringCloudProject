package com.dyhospital.cloudhis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
/**
 * @author yangzongxue
 */
@SpringBootApplication(scanBasePackages="com.dyhospital.cloudhis")
@EnableDiscoveryClient
@EnableHystrix
@EnableZuulProxy
@ServletComponentScan(basePackages = { "com.dyhospital.cloudhis"})
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
}
