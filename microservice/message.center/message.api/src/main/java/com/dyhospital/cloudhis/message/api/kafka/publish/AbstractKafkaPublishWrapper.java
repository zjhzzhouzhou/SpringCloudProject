package com.dyhospital.cloudhis.message.api.kafka.publish;

import com.dyhospital.cloudhis.common.web.exception.reg.exception.BizException;
import com.dyhospital.cloudhis.message.api.kafka.dto.KafkaMessage;
import com.dyhospital.cloudhis.message.api.kafka.dto.LoggerName;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: kafka 消息生产者适配器
 * User: zhouzhou
 * Date: 2019-05-07
 * Time: 5:19 PM
 */
public abstract class AbstractKafkaPublishWrapper implements IKafkaPublish {

    protected Logger logger = LoggerFactory.getLogger(LoggerName.KAFKA);


    @Override
    public <T> Boolean send(KafkaMessage<T> kafkaMessage) {
        if (kafkaMessage == null
                || StringUtils.isEmpty(kafkaMessage.getApplicationName())
                || StringUtils.isEmpty(kafkaMessage.getMessageId())
                || StringUtils.isEmpty(kafkaMessage.getTopic())
                || kafkaMessage.getContent() == null){
            throw new BizException(String.format("入参缺失,{%s}", kafkaMessage));

        }
        if (kafkaMessage.getAutoAck()){
            return publish(kafkaMessage);
        }else{
            return manualPublish(kafkaMessage);
        }


    }

    /**
     * 自动提交ack 通用发布消息方法
     * @param kafkaMessage
     * @param <T>
     * @return
     */
    public abstract <T> Boolean publish(KafkaMessage<T> kafkaMessage);

    /**
     *
     * @param kafkaMessage
     * @param <T>
     * @return
     */
    public abstract <T> Boolean manualPublish(KafkaMessage<T> kafkaMessage);

}
