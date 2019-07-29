package com.dyhospital.cloudhis.gateway.filter;

import com.dyhospital.cloudhis.gateway.constants.FilterType;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yangzongxue
 */
public class AccessFilter extends ZuulFilter {
    @Autowired
    protected Environment env;

    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);

    /**
     * 过滤器类型，它决定过滤器在请求的哪个生命周期执行。
     * 返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
     pre：可以在请求被路由之前调用
     routing：在路由请求时候被调用
     post：在routing和error过滤器之后被调用
     error：处理请求时发生错误时被调用


     这里定义pre，则过滤器会在请求被路由前执行
     * @return
     */
    @Override
    public String filterType() {
        return FilterType.PRE;
    }

    /**
     * 通过int值来定义过滤器的执行顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可实现过滤器的开关。在上例中，我们直接返回true，所以该过滤器总是生效。
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑。需要注意，这里我们通过
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String profile = env.getProperty("spring.profiles.active");
        if("prod".equals(profile)){
            String uri = request.getRequestURI();
            if(uri.contains("swagger-ui.html")
                    || uri.contains("v2/api-docs")
                    || uri.contains("swagger")){
                ctx.setSendZuulResponse(false);//令zuul过滤该请求，不对其进行路由
                ctx.setResponseStatusCode(HttpServletResponse.SC_FORBIDDEN);
            }
        }

//        try{
//            log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
//
//            Object accessToken = request.getParameter("accessToken");
//            if(accessToken == null) {
//                log.warn("access token is empty");
//                ctx.setSendZuulResponse(false);//令zuul过滤该请求，不对其进行路由
//                ctx.setResponseStatusCode(HttpServletResponse.SC_FORBIDDEN);//设置了其返回的错误码
////            ctx.setResponseBody(body)//对返回body内容进行编辑等
//                return null;
//            }
//            log.info("access token ok");
//        }catch (Exception e){
//            ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            ctx.set("error.exception", e);
//        }

        return null;
    }

}
