package com.dyhospital.cloudhis.message.controller;


import com.dyhospital.cloudhis.common.annotation.ZCachePut;
import com.dyhospital.cloudhis.common.annotation.ZCacheable;
import com.dyhospital.cloudhis.message.api.client.MessageApi;
import com.dyhospital.cloudhis.message.api.dto.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
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
@Api("缓存测试3333")
public class RedisTestController {

    private Logger logger = LoggerFactory.getLogger(RedisTestController.class);


    // ---------------------  字符串测试  ---------------------------
    @ApiOperation("缓存查询")
    @RequestMapping(value = MessageApi.REDIS_GET_STR, method = RequestMethod.POST)
    @ZCacheable(cacheName = "student", expireTime = 10, key = "#key")
    public String getStrValue(@RequestBody String key) {
        logger.info("获取中");
        return key + new Random().nextInt(100);
    }


    @ApiOperation("缓存更新")
    @RequestMapping(value = MessageApi.REDIS_UPDATE_STR, method = RequestMethod.POST)
    @ZCachePut(cacheName = "student", expireTime = 10, key = "#key")
    public String putStrValue(@RequestBody String key) {
        logger.info("更新中");
        return key + new Random().nextInt(100);
    }

    // ---------------------  对象测试  ---------------------------
    @ApiOperation("缓存查询根据类")
    @RequestMapping(value = MessageApi.REDIS_GET_STUDENT, method = RequestMethod.POST)
    @ZCacheable(cacheName = "student", expireTime = 10, key = "#student.name")
    public Object getStuValue(@RequestBody Student student) {
        logger.info("获取bean中的对象");
        Student studentResult = Student.builder().name(student.getName()).age(new Random().nextInt(100)).sex("男").build();
        return studentResult;
    }

    @ApiOperation("缓存更新根据类")
    @RequestMapping(value = MessageApi.REDIS_UPDATE_STUDENT, method = RequestMethod.POST)
    @ZCachePut(cacheName = "student", expireTime = 10, key = "#student.name")
    public Object updateStuValue(@RequestBody Student student) {
        logger.info("获取bean中的对象");
        Student studentResult = Student.builder().name(student.getName()).age(new Random().nextInt(100)).sex("男").build();
        return studentResult;
    }
}
