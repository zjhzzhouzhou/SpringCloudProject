package com.dyhospital.cloudhis.message.api.kafka.dto;

/**
 * Description: 日志记录器的名称
 * User: zhouzhou
 * Date: 2019-01-08
 * Time: 17:18
 */
public interface LoggerName {
    /**
     * 三方请求日志
     * <p>
     * Level : INFO , WARN
     * <p>
     * 输出 : message-third.log
     */
    String THIRD                               = "THIRD";


    /**
     * 短信日志
     * <p>
     * level : INFO
     * <p>
     * 输出 : message-sms.log
     */
    String SMS                    = "SMS";

    /**
     * kafka日志
     * <p>
     * level : INFO
     * <p>
     * 输出 : message-kafka.log
     */
    String KAFKA                     = "KAFKA";

    /**
     * 报错日志汇总
     * <p>
     * level : INFO
     * <p>
     * 输出 : message-error.log
     */
    String ERROR                     = "ERROR";
}
