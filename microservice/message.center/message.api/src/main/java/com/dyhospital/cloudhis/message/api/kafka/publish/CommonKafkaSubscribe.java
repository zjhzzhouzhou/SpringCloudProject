package com.dyhospital.cloudhis.message.api.kafka.publish;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Description:可以模仿着写,但是不能再次写逻辑
 * User: zhouzhou
 * Date: 2019-05-09
 * Time: 11:22 AM
 */
@Component("commonKafkaSubscribe")
public class CommonKafkaSubscribe extends AbstractKafkaSubscribeWrapper{

    /**
     * 这个是自动提交的消费方式
     * @param record
     * @throws Exception
     */
    //@KafkaListener(topics = TopicConstants.REG_PAY,groupId = "写自己的消费组 id")
    public void listenPay(ConsumerRecord<String, String> record) throws Exception {
        logger.info(String.format("kafka 消费消息成功---------------- listen1 topic = %s, offset = %d, value = %s ", record.topic(), record.offset(), record.value()));
        String msg = JSONObject.parseObject(record.value(), String.class);
        System.out.println(msg);
    }

    /**
     * 这是手动提交的消费方式
     * @param record
     * @param ack
     * @throws Exception
     */
    //@KafkaListener(topics = TopicConstants.COMMON_PAY,groupId = "写自己的消费组 id")
    public void listenXXXPay(ConsumerRecord<String, String> record , Acknowledgment ack) throws Exception {
        String msg = JSONObject.parseObject(record.value(), String.class);
        System.out.println(msg);
        if (new Random().nextInt(100)<50){
            logger.info(String.format("kafka 综合收费消费消息成功---------------- listen1 topic = %s, offset = %d, value = %s ", record.topic(), record.offset(), record.value()));
        }
        ack.acknowledge();
    }

}
