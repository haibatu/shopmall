package com.hbt.gateway.filter;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 限流过滤器
 */
@Slf4j
@Component
@SuppressWarnings("all")
public class RateLimiterFilter extends AbstractPreZuulFilter {

    //创建限流器，每秒可以 获取两个令牌
    RateLimiter rateLimiter = RateLimiter.create(2.0);

    @Override
    protected Object cRun() {
        HttpServletRequest request = context.getRequest();
        //成功获取令牌
        if (rateLimiter.tryAcquire()){
            log.info("get ratelimit token success");
            return success();
        }else{
            log.info("get ratelimit token fail", request.getRequestURI());
            return fail(402, "rate limit error");
        }
    }

    @Override
    public int filterOrder() {
        return 2;
    }
}
