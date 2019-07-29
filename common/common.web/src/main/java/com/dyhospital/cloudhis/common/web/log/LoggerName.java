package com.dyhospital.zjguahao.common.web.log;

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
     * 输出 : train-third.log
     */
    String THIRD                               = "THIRD";

    /**
     * 流程日志
     * <p>
     * Level : INFO , WARN
     * </p>
     * 输出 : train-process.log
     */
    String PROCESS                           = "PROCESS";

    /**
     * 内部微服务调用日志
     * <p>
     * level : INFO
     * <p>
     * 输出 : train-inner.log
     */
    String INNER                    = "INNER";

    /**
     * 内部远程调用日志
     * <p>
     * level : INFO
     * <p>
     * 输出 : train-remote.log
     */
    String REMOTE                     = "REMOTE";

    /**
     * 报错日志汇总
     * <p>
     * level : INFO
     * <p>
     * 输出 : train-error.log
     */
    String ERROR                     = "ERROR";
}
