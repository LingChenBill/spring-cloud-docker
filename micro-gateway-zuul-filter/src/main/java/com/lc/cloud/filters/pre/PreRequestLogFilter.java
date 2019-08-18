package com.lc.cloud.filters.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * Zuul pre filters.
 *
 * @author zyz.
 */
@Slf4j
public class PreRequestLogFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        // 打印请求的HTTP的方法以及请求的地址。
        log.info(String.format("send %s request to %s",
                request.getMethod(),
                request.getRequestURL().toString()));

        return null;
    }
}
