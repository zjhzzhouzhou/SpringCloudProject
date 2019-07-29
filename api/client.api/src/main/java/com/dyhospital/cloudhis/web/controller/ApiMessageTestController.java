package com.dyhospital.cloudhis.web.controller;

import com.dyhospital.cloudhis.common.web.annotation.method.GenericResponse;
import com.dyhospital.cloudhis.message.api.client.MessageApi;
import com.dyhospital.cloudhis.message.api.client.feign.CustomerMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author panda
 */
@Api(tags = "Message Controller",
        value = "Message api")
@RestController
@RequestMapping("test")
public class ApiMessageTestController {
    private static final Logger logger = LoggerFactory.getLogger(ApiMessageTestController.class);

    @Autowired
    private CustomerMessageService customerMessageService;

    @ApiOperation(value = "=发布通用消息", notes = "发布通用消息")
    @PostMapping(value = MessageApi.SEND_MESSAGE_V1, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericResponse<Boolean> publishMessage(Integer counts) {
        return customerMessageService.publishMessage(counts);

    }

}
