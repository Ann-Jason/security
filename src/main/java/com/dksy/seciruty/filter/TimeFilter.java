package com.dksy.seciruty.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

//@Component
@Slf4j
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("time filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("time filter start");
        long time = new Date().getTime();
        chain.doFilter(request,response);
        log.info("time filter:{}",new Date().getTime()-time);
        log.info("time filter finish");

    }

    @Override
    public void destroy() {
        log.info("time filter destroy");
    }
}
