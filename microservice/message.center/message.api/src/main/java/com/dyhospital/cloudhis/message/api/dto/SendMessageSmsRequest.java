package com.dyhospital.cloudhis.message.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * @program: zjguahao
 * @description: 发送短信
 * @author: zhouzhou
 * @create: 2019/04/18 14:38
 **/
@Data
@ApiModel(description= "发送短信")
public class SendMessageSmsRequest {

    @ApiModelProperty(value = "场景值")
    private String sence;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "可变参数")
    private Map<String, String> param;
}