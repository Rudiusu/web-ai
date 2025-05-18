package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    /**
     * 测试生成JWT令牌
     */
//    @Test
//    public void testGenerateJWT(){
//        Map<String, Object> dataMap = new HashMap<>();
//        dataMap.put("id",1);
//        dataMap.put("username","rudiusu");
//
//        String token = Jwts.builder()
//                .signWith(SignatureAlgorithm.HS256,"cnVkaXVzdQ==")//指定签名算法以及密钥
//                .addClaims(dataMap) //添加自定义数据
//                .setExpiration(new Date(System.currentTimeMillis()+30*1000))//设置过期时间
//                .compact();//生成令牌
//        System.out.println(token);
//    }

    /**
     * 测试解析JWT令牌
     */
//    @Test
//    public void testParseJWT(){
//        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJydWRpdXN1IiwiZXhwIjoxNzQ3Mzc4MzM2fQ.RmcEuzN-VsvDP7wMRvcB7BQIeVaRZWg5LhzzGanpgLk";
//        Claims claims = Jwts.parser().setSigningKey("cnVkaXVzdQ==").parseClaimsJws(token).getBody();
//        System.out.println(claims);
//    }
}
