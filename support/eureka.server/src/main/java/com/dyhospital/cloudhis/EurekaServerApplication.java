package com.dyhospital.cloudhis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
/**
 * @author yangzongxue
 */
@EnableEurekaServer
@SpringBootApplication(scanBasePackages="com.dyhospital.cloudhis")
@ServletComponentScan(basePackages = { "com.dyhospital.cloudhis"})
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}

