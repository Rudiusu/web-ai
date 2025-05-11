package com.itheima.controller;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    @PostMapping("/upload")
    public Result updload( MultipartFile file) throws Exception{
        String fileType="";
        String fileName = file.getOriginalFilename();
        if (fileName != null) {
            fileType = "."+fileName.split("\\.")[1];
        }
//        log.info("接收参数：{},{},{}",name,age,file);
        file.transferTo(new File("D:/image/"+ UUID.randomUUID()+fileType));
       return Result.success();
    }
}
