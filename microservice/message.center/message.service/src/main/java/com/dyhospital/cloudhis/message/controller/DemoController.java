package com.dyhospital.cloudhis.message.controller;

import com.dyhospital.cloudhis.common.web.annotation.method.GenericResponse;
import com.dyhospital.cloudhis.message.api.client.MessageApi;
import com.dyhospital.cloudhis.message.api.kafka.dto.KafkaMessage;
import com.dyhospital.cloudhis.message.api.kafka.dto.TopicConstants;
import com.dyhospital.cloudhis.message.api.kafka.publish.IKafkaPublish;
import com.dyhospital.cloudhis.message.api.kafka.util.KafkaUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangzongxue
 */
@Api(tags = "Demo Controller", value = "Demo api")
@RestController
public class DemoController {

    @Autowired
    private IKafkaPublish kafkaPublish;

    @ApiOperation(value = "=发布通用消息", notes = "发布通用消息")
    @PostMapping(MessageApi.SEND_MESSAGE_V1)
    public GenericResponse<Boolean> publishMessage(@RequestBody Integer counts) {

        GenericResponse<Boolean> response = new GenericResponse<>();
        Integer success = 0;

        for (int i = 0; i < counts; i++) {

            KafkaMessage<Object> kafkaMessage = KafkaMessage.builder().applicationName("message")
                    .messageId(KafkaUtils.generateMessageId())
                     .topic(TopicConstants.REG_PAY)
                    .autoAck(true)
                    .content("hahhahhaha").build();
            Boolean flag = kafkaPublish.send(kafkaMessage);

            if (flag) {
                success++;
            }
        }

        response.setResult(true);
        response.setMessage(String.format("发送消息,成功{%s},失败{%s}", success, counts - success));
        return response;

    }


}
