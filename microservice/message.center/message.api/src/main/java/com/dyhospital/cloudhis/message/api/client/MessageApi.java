package com.dyhospital.cloudhis.message.api.client;

/**
 * @author zhouzhou
 * @date 2019-7-01 16:52:38
 * 服务列表
 */
public interface MessageApi {


    String APPLICATION_NAME = "message-service";


    String SEND_MESSAGE_V1 = "/msg/sendMessageV1";


    String REDIS_GET_STR = "/getValue/str";

    String REDIS_UPDATE_STR = "/updateValue/str";

    String REDIS_GET_STUDENT = "/getValue/stu";

    String REDIS_UPDATE_STUDENT = "/updateValue/stu";



}
