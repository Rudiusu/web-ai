package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    //本地存储方案
//    @PostMapping("/upload")
//    public Result updload( MultipartFile file) throws Exception{
//        String fileType="";
//        String fileName = file.getOriginalFilename();
//        if (fileName != null) {
//            fileType = "."+fileName.split("\\.")[1];
//        }
//
//        file.transferTo(new File("D:/image/"+ UUID.randomUUID()+fileType));
//       return Result.success();
//    }
    AliyunOSSOperator ossOperator ;
    @Autowired
    public UploadController(AliyunOSSOperator ossOperator) {
        this.ossOperator = ossOperator;
    }
    @PostMapping("/upload")
    public Result updload( MultipartFile file) throws Exception{
        String fileName = file.getOriginalFilename();
        String filePath = "";
        log.info("文件名称：{}", fileName);
        if (fileName != null) {
            filePath = ossOperator.upload(file.getBytes(), fileName);
        }
        log.info("OSS:{}", filePath);
        return Result.success("success",filePath);
    };


}
