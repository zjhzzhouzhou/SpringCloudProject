/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.dyhospital.cloudhis.common.web.exception.reg.exception;

/**
 * 错误码接口
 * @author jacques
 * @version $Id: ErrorCode.java, v 0.1 2016年4月28日 下午3:50:57 jacques Exp $
 */
public interface ErrorCode {

    /**
     * 获取错误码
     * @return
     */
    String getCode();

    /**
     * 获取错误信息
     * @return
     */
    String getDescription();
}
