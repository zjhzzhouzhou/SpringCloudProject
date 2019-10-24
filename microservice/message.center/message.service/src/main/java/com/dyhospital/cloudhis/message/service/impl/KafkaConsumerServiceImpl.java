package com.dyhospital.cloudhis.message.service.impl;


import com.dyhospital.cloudhis.message.api.kafka.dto.GroupIdConstants;
import com.dyhospital.cloudhis.message.api.kafka.dto.TopicConstants;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

/**
 * kafka 监听
 *
 * @author zhouzhou
 * @date 2019-7-01 16:52:38
 */
@Component
public class KafkaConsumerServiceImpl {

    private Logger logger = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);

    // 测试专用
    @KafkaListener(topics = TopicConstants.REG_PAY, groupId = GroupIdConstants.MESSAGE)
    public void listenPay(ConsumerRecord<String, byte[]> record) {
        logger.info(String.format("kafka 消费消息成功---------------- listen1 topic = %s, offset = %d, value = %s ", record.topic(), record.offset(), record.value()));
        Object msg = SerializationUtils.deserialize(record.value());
        System.out.println(msg);
    }

}
