package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan //开启servlet组件扫描
@SpringBootApplication
public class WebAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebAiApplication.class, args);
    }

}

