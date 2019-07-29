package com.dyhospital.cloudhis.message.api.kafka.publish;

import com.dyhospital.cloudhis.message.api.kafka.dto.LoggerName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:
 * User: zhouzhou
 * Date: 2019-05-09
 * Time: 11:16 AM
 */
public class AbstractKafkaSubscribeWrapper implements IKafkaSubscribe {
    protected Logger logger = LoggerFactory.getLogger(LoggerName.KAFKA);

}
