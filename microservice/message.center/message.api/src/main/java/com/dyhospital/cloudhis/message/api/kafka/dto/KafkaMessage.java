package com.dyhospital.cloudhis.message.api.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description:消息通用类
 * User: zhouzhou
 * Date: 2019-05-07
 * Time: 4:47 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KafkaMessage<T> implements Serializable {

    private static final long serialVersionUID = 7905262263441625598L;

    /** 消息 id */
    private String messageId;

    /** 应用名 */
    private String applicationName;

    /**
     * 消息主题
     * @see TopicConstants
     */
    private String topic;

    /** 消息体 */
    private T content;

    /** 是否自动提交,默认自动提交 */
    private Boolean autoAck = true;
}
