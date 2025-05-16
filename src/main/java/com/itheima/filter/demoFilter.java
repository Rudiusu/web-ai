package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
//@WebFilter(urlPatterns = "/*")
@Slf4j
public class demoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化逻辑（可选）
        log.info("filter初始化完成");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        // 请求前处理逻辑（如编码、日志等）
        log.info("拦截请求");
        // 继续过滤器链或目标资源
        chain.doFilter(request, response);

        // 响应后处理逻辑（可选）
    }

    @Override
    public void destroy() {
        // 销毁时释放资源（可选）
        log.info("filter销毁");
    }
}
