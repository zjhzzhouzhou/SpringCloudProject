package com.dyhospital.cloudhis.message.api.kafka.publish;

import com.alibaba.fastjson.JSONObject;
import com.dyhospital.cloudhis.message.api.kafka.dto.KafkaMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * Description:通用 kafka 发布
 * <p>
 * 分为手动和自动,默认为自动 acks,对消息到发要求极高,= 采用手动 ack
 * </p>
 * User: zhouzhou
 * Date: 2019-05-07
 * Time: 5:25 PM
 */
@Component("commonKafkaPublish")
public class CommonKafkaPublish extends AbstractKafkaPublishWrapper {


    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private KafkaSendResultHandler producerListener;


    @Override
    public <T> Boolean publish(KafkaMessage<T> kafkaMessage) {

        String applicationName = kafkaMessage.getApplicationName();
        String topic = kafkaMessage.getTopic();
        String messageId = kafkaMessage.getMessageId();
        Object content = kafkaMessage.getContent();

        // String kafkaValue = JSONObject.toJSONString(content);
        byte[] serialize = SerializationUtils.serialize(content);
        try {
            kafkaTemplate.send(topic, serialize);
        } catch (Exception e) {
            logger.error(String.format("kafka 调用发布出现异常,messageId{%s},发起服务为{%s},topic 为{%s},消息体为{%s}", messageId, applicationName, topic, content), e);
            return false;
        }
        logger.info(String.format("kafka 发布消息成功,messageId{%s},发起服务为{%s},topic 为{%s},消息体为{%s}", messageId, applicationName, topic, content));

        return true;
    }

    @Override
    public <T> Boolean manualPublish(KafkaMessage<T> kafkaMessage) {
        // 设置结果回调监听
        kafkaTemplate.setProducerListener(producerListener);

        String applicationName = kafkaMessage.getApplicationName();
        String topic = kafkaMessage.getTopic();
        String messageId = kafkaMessage.getMessageId();
        Object content = kafkaMessage.getContent();

        String kafkaValue = JSONObject.toJSONString(content);

        // 同步发送
        try {
            ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, kafkaValue);
            future.get();
        } catch (Exception e) {
            logger.error(String.format("kafka 调用出现异常,messageId{%s},发起服务为{%s},topic 为{%s},消息体为{%s}", messageId, applicationName, topic, kafkaValue));
            return false;
        }

        return true;

    }
}
