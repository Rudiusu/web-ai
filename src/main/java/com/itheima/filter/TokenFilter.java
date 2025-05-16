package com.itheima.filter;

import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
         //获取请求路径
         //判断是否为登录请求，根据路径是否包含/login判断，如果是则放行
         if(uri.contains("/login")){
             filterChain.doFilter(servletRequest,servletResponse);
             return;
         }
         //获取请求头中的token
         String token = request.getHeader("token");
         //判断token是否存在，如果不存在，返回错误信息（响应401状态码）
         if(token==null || token.isEmpty()){
             log.info("令牌不存在");
             response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
             return;
         }else {
             //如果token存在，解析并校验token，如果校验失败，返回错误信息（响应401状态码）
             try{
                 Claims claims = JwtUtils.parseJWT(token);
             }catch (MalformedJwtException e){
                 log.info("令牌非法");
                 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                 return;
             }
             //如果校验通过,则放行
             filterChain.doFilter(servletRequest,servletResponse);
         }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
