package com.dyhospital.cloudhis.gateway.filter;

import com.dyhospital.cloudhis.common.web.constants.BaseResultCode;
import com.dyhospital.cloudhis.common.web.constants.ErrorType;
import com.netflix.zuul.context.RequestContext;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
/**
 * @author yangzongxue
 */
@Component
public class ZuulErrorAttribute extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        RequestContext ctx = RequestContext.getCurrentContext();
        Throwable t =  (Throwable)ctx.get("error.exception");
        Map<String, Object> result = super.getErrorAttributes(webRequest, includeStackTrace);
        result.put("code", BaseResultCode.SYSTEM_ERROR);
        result.put("errorType", ErrorType.SYS_ERROR_TYPE);
        result.put("message","system error");
        result.put("result",null);
        return result;

    }


}