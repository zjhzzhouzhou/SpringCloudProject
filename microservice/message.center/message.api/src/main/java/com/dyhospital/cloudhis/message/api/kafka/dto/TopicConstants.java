package com.dyhospital.cloudhis.message.api.kafka.dto;

/**
 * Description: 消息 topic
 * User: zhouzhou
 * Date: 2019-05-07
 * Time: 4:45 PM
 */
public interface TopicConstants {

    /**
     * 挂号支付成功
     */
    String REG_PAY = "reg_pay";
    /**
     * 挂号取消
     */
    String REG_CANCEL = "reg_cancel";

    /**
     * 当如挂号结果
     */
    String REG_RESULT = "reg_result_notify";


    /**
     * 建行支付结果通知
     */
    String CCB_PAY_RESULT_NOTIFY = "ccb_hsb_pay_result_notify";

    /**
     * 建行退款结果通知
     */
    String CCB_REFUND_RESULT_NOTIFY = "ccb_hsb_refund_result_notify";

    /**
     * 建行(政融)支付结果通知
     */
    String CCB_ZR_PAY_RESULT_NOTIFY = "ccb_zr_pay_result_notify";
    /**
     * 短消息发送v1
     */
    String SEND_MESSAGE_V1 = "send_message_v1";

    /**
     * 主题持有：主动查询建行(政融)退款详情，退款状态变动时，发布的消息
     */
    String CCB_ZR_QUERY_REFUND_APPLY = "ccb_zr_query_refund_apply";

}