package com.xinchen.redis.servlet;

import com.xinchen.redis.core.annotation.Log;
import org.slf4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Filter的执行顺序为类名匹配 A-Z
 * AFilter 比 BFilter先执行
 *
 * @author xinchen
 * @version 1.0
 * @date 06/12/2018 10:15
 */
@WebFilter(filterName = "testFilter",urlPatterns = "/*")
public class TestFilter implements Filter {

    @Log(description = "test")
    private static Logger log;

    public static void setLog(Logger log) {
        TestFilter.log = log;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info(">>> Filter : test filter init.");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info(">>> Filter : test filter working.");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info(">>> Filter : test filter destroy.");
    }
}
