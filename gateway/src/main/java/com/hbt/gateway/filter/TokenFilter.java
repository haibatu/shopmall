package com.hbt.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 校验请求中的token
 */
@Slf4j
@Component
public class TokenFilter extends AbstractPreZuulFilter {
    @Override
    protected Object cRun() {
        HttpServletRequest request = context.getRequest();
        log.info(String.format("%s request %s",
                request.getMethod(),request.getRequestURI().toString()));
        Object token = request.getParameter("token");
        if (null == token){
            log.info("token is empty");
            return fail(401, "token is empty");
        }
        return success();
    }

    @Override
    public int filterOrder() {
        //数字越小，优先级越高
        return 1;
    }
}
