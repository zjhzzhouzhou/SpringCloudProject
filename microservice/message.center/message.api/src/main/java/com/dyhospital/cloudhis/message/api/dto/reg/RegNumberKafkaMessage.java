package com.dyhospital.cloudhis.message.api.dto.reg;

import lombok.Data;

/**
 * Description:挂号结果kafka消息
 * User: zhouzhou
 * Date: 2019-05-21
 * Time: 15:59
 */
@Data
public class RegNumberKafkaMessage {

    // 200 成功, 500失败, 300 取消
    private String code;
    // 失败/成功的消息
    private String message;
    // orderId
    private String orderId;

}
