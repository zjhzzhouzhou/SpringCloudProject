package com.dyhospital.cloudhis.common.web.exception.handler;


import com.dyhospital.cloudhis.common.web.annotation.method.GenericResponse;
import com.dyhospital.cloudhis.common.web.constants.BaseResultCode;
import com.dyhospital.cloudhis.common.web.constants.ErrorType;
import com.dyhospital.cloudhis.common.web.exception.reg.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerAdviceProcessor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public GenericResponse<?> handleException(HttpServletRequest request, Exception ex) {

        String code = BaseResultCode.SYSTEM_ERROR;
        String errorType = ErrorType.SYS_ERROR_TYPE;
        if(ex instanceof HttpMessageNotReadableException){
            code = BaseResultCode.PARAM_ERROR;
        }else if(ex instanceof HttpRequestMethodNotSupportedException){
            code = BaseResultCode.HTTP_METHOD_NOT_SURPPORT;
        }
        String msg = null;
        if(ex instanceof BizException){
            BizException bizException = (BizException) ex;
            msg = bizException.getMessage();
            code = bizException.getErrorCode().getCode();

        }else{
            errorType = ErrorType.SYS_ERROR_TYPE;
        }
/*        if(ex instanceof UnauthorizedException){
             code = BaseResultCode.NO_PERMISSIONS;
            errorType = ErrorType.SYS_ERROR_TYPE;
        }*/
        if(msg == null)
        {
            msg = getMsgByException(code, ex, messageSource);
        }
        GenericResponse<?> resp = new GenericResponse<>();
        resp.setCode(code);
        resp.setErrorType(ErrorType.BUSI_ERROR_TYPE);
        resp.setMessage(msg);
        logger.error("code: "+ code +"  msg: " + msg, ex);
        return resp;
    }



    private String getMsgByException(String code, Throwable ex, MessageSource messageSource) {
        try {
            return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
        }
        catch (NoSuchMessageException e) {
        }

        return "";
    }
}
