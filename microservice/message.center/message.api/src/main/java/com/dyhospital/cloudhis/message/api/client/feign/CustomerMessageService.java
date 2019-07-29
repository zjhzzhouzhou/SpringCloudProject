package com.dyhospital.cloudhis.message.api.client.feign;

import com.dyhospital.cloudhis.common.web.annotation.method.GenericResponse;
import com.dyhospital.cloudhis.message.api.client.MessageApi;
import com.dyhospital.cloudhis.message.api.dto.SendMessageSmsRequest;
import com.dyhospital.cloudhis.message.api.dto.Student;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Description: messsage
 * User: zhouzhou
 * Date: 2019-05-10
 * Time: 5:04 PM
 */
@Component
@FeignClient(value = MessageApi.APPLICATION_NAME, fallback = CustomerMessageServiceHystrix.class)
public interface CustomerMessageService {

    @ApiOperation(value = "=发布通用消息", notes = "发布通用消息")
    @PostMapping(MessageApi.SEND_MESSAGE_V1)
    GenericResponse<Boolean> publishMessage(@RequestBody Integer counts);

    @ApiOperation("缓存查询")
    @RequestMapping(value = MessageApi.REDIS_GET_STR, method = RequestMethod.POST)
    String getStrValue(@RequestBody String key);


    @ApiOperation("缓存更新")
    @RequestMapping(value = MessageApi.REDIS_UPDATE_STR, method = RequestMethod.POST)
    String putStrValue(@RequestBody String key);

    @ApiOperation("缓存查询根据类")
    @RequestMapping(value = MessageApi.REDIS_GET_STUDENT, method = RequestMethod.POST)
    Object getStuValue(@RequestBody Student student);

    @ApiOperation("缓存更新根据类")
    @RequestMapping(value = MessageApi.REDIS_UPDATE_STUDENT, method = RequestMethod.POST)
    Object updateStuValue(@RequestBody Student student);
}