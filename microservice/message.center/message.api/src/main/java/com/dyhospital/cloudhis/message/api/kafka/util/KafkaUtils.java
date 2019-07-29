package com.dyhospital.cloudhis.message.api.kafka.util;

import java.util.UUID;

/**
 * Description:
 * User: zhouzhou
 * Date: 2019-05-09
 * Time: 11:34 AM
 */
public class KafkaUtils {

    /**
     * 生成 messageId
     * @return
     */
    public static String generateMessageId(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
