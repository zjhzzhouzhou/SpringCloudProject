package com.dyhospital.cloudhis.common.web.annotation.method;

import com.dyhospital.cloudhis.common.web.constants.BaseResultCode;
import com.dyhospital.cloudhis.common.web.exception.reg.exception.BizErrorCodeEnum;
import com.dyhospital.cloudhis.common.web.exception.reg.exception.BizException;
import lombok.Data;

/**
 * 通用响应
 * @param <T>
 */
@Data
public class GenericResponse<T> {

    // 错误类型
    private String code;
    private String message;
    // 错误类型，如果是BUSI_ERROR，则为业务异常，业务异常不打印异常日志。
    private String errorType;
    private T result;


    public GenericResponse() {
        super();
    }

    public GenericResponse(T result) {
        this(BaseResultCode.SUCCESS, BaseResultCode.SUCCESS_MSG, result);
    }

    public GenericResponse(String code,
                           String message, T result) {
        super();
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public GenericResponse(String requestId, String code,
                           String message, T result, String errorType) {
        super();
        this.code = code;
        this.message = message;
        this.result = result;
        this.errorType = errorType;
    }

    public static void assertResponse(GenericResponse response){
        if (!response.getCode().equals(BaseResultCode.SUCCESS)) {
            BizErrorCodeEnum codeEnum = BizErrorCodeEnum.getByCode(response.getCode());
            throw new BizException(codeEnum);
        }
    }
}
