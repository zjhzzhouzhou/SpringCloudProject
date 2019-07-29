package com.dyhospital.cloudhis.message.api.kafka.publish;

import com.dyhospital.cloudhis.message.api.kafka.dto.KafkaMessage;

/**
 * Description:通用 kafka 规范
 * User: zhouzhou
 * Date: 2019-05-09
 * Time: 10:30 AM
 */
public interface IKafkaPublish {

    /**
     * kafka发送消息
     * @param kafkaMessage 通用kafka 消息
     * @param <T> 内容
     * @return 成功或者失败
     */
    <T> Boolean send(KafkaMessage<T> kafkaMessage);

}
