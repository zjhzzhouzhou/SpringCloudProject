package com.dyhospital.cloudhis.web.controller;


import com.dyhospital.cloudhis.common.annotation.ZCachePut;
import com.dyhospital.cloudhis.common.annotation.ZCacheable;
import com.dyhospital.cloudhis.message.api.client.MessageApi;
import com.dyhospital.cloudhis.message.api.client.feign.CustomerMessageService;
import com.dyhospital.cloudhis.message.api.dto.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * Description:
 * User: zhouzhou
 * Date: 2019-06-03
 * Time: 17:47
 */
@RestController
@Api("缓存测试")
@RequestMapping("/test")
public class ApiRedisTestController {

    @Value("${test.name}")
    private String testName;

    private Logger logger = LoggerFactory.getLogger(ApiRedisTestController.class);

    @Autowired
    private CustomerMessageService customerMessageService;

    // ---------------------  字符串测试  ---------------------------
    @ApiOperation("配置文件测试")
    @RequestMapping(value = "/config", method = RequestMethod.GET)
    public String getConfigValue(String name) {
        int i = 0;
        return testName + "+" + name;
    }

    @ApiOperation("缓存查询")
    @RequestMapping(value = MessageApi.REDIS_GET_STR, method = RequestMethod.GET)
    public String getStrValue(String key) {
        int i = 0;
        return customerMessageService.getStrValue(key);
    }

    @ApiOperation("缓存更新")
    @RequestMapping(value = MessageApi.REDIS_UPDATE_STR, method = RequestMethod.POST)
    public String putStrValue(String key) {
        return customerMessageService.putStrValue(key);
    }

    // ---------------------  对象测试  ---------------------------
    @ApiOperation("缓存查询根据类")
    @RequestMapping(value = MessageApi.REDIS_GET_STUDENT, method = RequestMethod.GET)
    public Object getStuValue(Student student) {
        return customerMessageService.getStuValue(student);
    }

    @ApiOperation("缓存更新根据类")
    @RequestMapping(value = MessageApi.REDIS_UPDATE_STUDENT, method = RequestMethod.POST)
    public Object updateStuValue(Student student) {
        return customerMessageService.updateStuValue(student);
    }
}
