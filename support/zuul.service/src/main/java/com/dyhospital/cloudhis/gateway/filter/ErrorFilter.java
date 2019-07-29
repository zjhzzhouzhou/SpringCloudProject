package com.dyhospital.cloudhis.gateway.filter;

import com.dyhospital.cloudhis.gateway.constants.FilterType;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
/**
 * @author yangzongxue
 */
public class ErrorFilter extends ZuulFilter {
    private static final String ERROR_STATUS_CODE_KEY = "error.status_code";
    protected static final String SEND_ERROR_FILTER_RAN = "sendErrorFilter.ran";
    public static final String DEFAULT_ERR_MSG = "系统繁忙,请稍后再试";
    private static Logger log = LoggerFactory.getLogger(ErrorFilter.class);

    @Override
    public String filterType() {
        return FilterType.ERROR;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        log.info("进入错误异常的过滤器！");
        RequestContext ctx = RequestContext.getCurrentContext();
        Throwable throwable = getOriginException(ctx.getThrowable());
//        log.error("this is a ErrorFilter : {}", throwable.getCause().getMessage());
        ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        ctx.set("error.exception", throwable.getCause());
        return null;
    }



    private Throwable getOriginException(Throwable e){
        e = e.getCause();
        while(e.getCause() != null){
            e = e.getCause();
        }
        return e;
    }

}