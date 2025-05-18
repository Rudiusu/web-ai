package com.itheima.interceptor;

import com.itheima.utils.CurrentHolder;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Date;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取请求头中的token
        String token = request.getHeader("token");
        //判断token是否存在，如果不存在，返回错误信息（响应401状态码）
        if (token == null || token.isEmpty()) {
            log.info("令牌不存在");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        } else {
            //如果token存在，解析并校验token，如果校验失败，返回错误信息（响应401状态码）
            try {
                Claims claims = JwtUtils.parseJWT(token);
                Integer id = Integer.valueOf(claims.get("id").toString()) ;
                Date endTime = claims.getExpiration();
                Date currentTime = new Date();
                CurrentHolder.setCurrentId(id);
                log.info("令牌信息：{}，{}",claims,endTime.compareTo(currentTime));
                if(endTime.compareTo(currentTime)<0){
                    log.info("令牌过期");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return false;
                }
            } catch (MalformedJwtException e) {
                log.info("令牌非法");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
            //如果校验通过,则放行

            return true;
        }
    }
}
