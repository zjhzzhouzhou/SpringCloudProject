package com.dyhospital.cloudhis.common.web.constants;

public class BaseResultCode {
    /*系统异常码*/
    public static final String SUCCESS = "200";
    public static final String SUCCESS_MSG = "success";

    public static final String UNKOWN_ERROR = "404";
    public static final String UNKOWN_ERROR_MSG = "unkown error";

    public static final String SYSTEM_ERROR = "500";
    public static final String SYSTEM_ERROR_MSG = "system error";




    /*通用业务异常码*/
    public static final String PARAM_ERROR = "900";//参数错误
    public static final String HTTP_METHOD_NOT_SURPPORT = "901";//HTTP方法不支持
    public static final String GET_REDIS_LOCK_ERROR = "902";//获取redis分布式锁失败
    public static final String NO_PERMISSIONS = "903";//无权访问
    public static final String REPEATSUBMIT = "904";//重复提交

    public static final String THIRD_IP_WHITE_CHECK_ERROR = "905";//ip白名单校检失败


}
